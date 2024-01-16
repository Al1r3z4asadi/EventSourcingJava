package com.example.tv2.core.subscription;


import com.eventstore.dbclient.*;
import com.example.tv2.core.events.eventbus.EventBus;
import com.example.tv2.core.events.EventEnvelope;
import com.example.tv2.core.events.eventbus.EventTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.support.RetryTemplate;


public class ESDBSubscriptionToAll{
    private final EventStoreDBClient eventStoreClient;
    private final SubscriptionCheckpointRepository checkpointRepository;
    private final EventBus eventBus;
    private EventStoreDBSubscriptionToAllOptions subscriptionOptions;
    private final Object resubscribeLock = new Object();
    private Subscription subscription;
    private boolean isRunning;
    private final Logger logger = LoggerFactory.getLogger(ESDBSubscriptionToAll.class);

    private final RetryTemplate retryTemplate = RetryTemplate.builder()
            .infiniteRetry()
            .exponentialBackoff(100, 2, 5000)
            .build();

    private final SubscriptionListener listener = new SubscriptionListener() {
        @Override
        public void onEvent(Subscription subscription, ResolvedEvent event) {
            try {
                handleEvent(event);
            } catch (Throwable e) {
                logger.error("Error while handling event", e);
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onError(Subscription subscription, Throwable throwable) {
            logger.error("Subscription was dropped", throwable);

            throw new RuntimeException(throwable);
        }
    };

    public ESDBSubscriptionToAll(
            EventStoreDBClient eventStoreClient,
            SubscriptionCheckpointRepository checkpointRepository,
            EventBus eventBus
    ) {
        this.eventStoreClient = eventStoreClient;
        this.checkpointRepository = checkpointRepository;
        this.eventBus = eventBus;
    }

    public void subscribeToAll() {
        subscribeToAll(EventStoreDBSubscriptionToAllOptions.getDefault());
    }

    void subscribeToAll(EventStoreDBSubscriptionToAllOptions subscriptionOptions) {
        this.subscriptionOptions = subscriptionOptions;

        try {
            retryTemplate.execute(context -> {
                var checkpoint = checkpointRepository.load(subscriptionOptions.getSubscriptionId());

                if (!checkpoint.isEmpty()) {
                    subscriptionOptions.getSubscribeToAllOptions()
                            .fromPosition(new Position(checkpoint.get(), checkpoint.get()));
                } else {
                    subscriptionOptions.getSubscribeToAllOptions()
                            .fromStart();
                }

                logger.info("Subscribing to all '%s'".formatted(subscriptionOptions.getSubscriptionId()));

                subscription = eventStoreClient.subscribeToAll(
                        listener,
                        subscriptionOptions.getSubscribeToAllOptions()
                ).get();
                return null;
            });
        } catch (Throwable e) {
            logger.error("Error while starting subscription", e);
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        if (!isRunning)
            return;

        isRunning = false;
        subscription.stop();
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    private void handleEvent(ResolvedEvent resolvedEvent) {
        if (isEventWithEmptyData(resolvedEvent) || isCheckpointEvent(resolvedEvent))
            return;

        var eventClass = EventTypeMapper.toClass(resolvedEvent.getEvent().getEventType());

        var streamEvent = eventClass.flatMap(c -> EventEnvelope.of(c, resolvedEvent));

        if (streamEvent.isEmpty()) {
            // That can happen if we're sharing database between modules.
            // If we're subscribing to all and not filtering out events from other modules,
            // then we might get events that are from other module, and we might not be able to deserialize them.
            // In that case it's safe to ignore deserialization error.
            // You may add more sophisticated logic checking if it should be ignored or not.
            logger.warn("Couldn't deserialize event with id: %s".formatted(resolvedEvent.getEvent().getEventId()));

            if (!subscriptionOptions.isIgnoreDeserializationErrors())
                throw new IllegalStateException(
                        "Unable to deserialize event %s with id: %s"
                                .formatted(resolvedEvent.getEvent().getEventType(), resolvedEvent.getEvent().getEventId())
                );

            return;
        }

        // publish event to internal event bus
        eventBus.publish((EventEnvelope<?>) streamEvent.get());

        checkpointRepository.store(
                this.subscriptionOptions.getSubscriptionId(),
                resolvedEvent.getEvent().getPosition().getCommitUnsigned()
        );
    }

    private boolean isEventWithEmptyData(ResolvedEvent resolvedEvent) {
        if (resolvedEvent.getEvent().getEventData().length != 0) return false;

        logger.info("Event without data received");
        return true;
    }

    private boolean isCheckpointEvent(ResolvedEvent resolvedEvent) {
        if (!resolvedEvent.getEvent().getEventType().equals(EventTypeMapper.toName(Checkpoint.class)))
            return false;

        logger.info("Checkpoint event - ignoring");
        return true;
    }
}