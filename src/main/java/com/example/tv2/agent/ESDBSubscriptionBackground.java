package com.example.tv2.agent;


import com.eventstore.dbclient.EventStoreDBClient;
import com.example.tv2.core.events.eventbus.EventBus;
import com.example.tv2.core.subscription.ESDBSubscriptionToAll;
import com.example.tv2.core.subscription.SubscriptionCheckpointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;

public class ESDBSubscriptionBackground implements SmartLifecycle {
    private final SubscriptionCheckpointRepository subscriptionCheckpointRepository;
    private final EventStoreDBClient eventStore;
    private final EventBus eventBus;
    private ESDBSubscriptionToAll subscription;
    private final Logger logger = LoggerFactory.getLogger(ESDBSubscriptionBackground.class);

    public ESDBSubscriptionBackground(
            EventStoreDBClient eventStore,
            SubscriptionCheckpointRepository subscriptionCheckpointRepository,
            EventBus eventBus
    ) {
        this.eventStore = eventStore;
        this.subscriptionCheckpointRepository = subscriptionCheckpointRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void start() {
        try {
            subscription = new ESDBSubscriptionToAll(
                    eventStore,
                    subscriptionCheckpointRepository,
                    eventBus
            );
            subscription.subscribeToAll();
        } catch (Throwable e) {
            logger.error("Failed to start Subscription to All", e);
        }
    }

    @Override
    public void stop() {
        stop(() -> {});
    }

    @Override
    public boolean isRunning() {
        return subscription != null && subscription.isRunning();
    }

    @Override
    public int getPhase() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        if (!isRunning()) return;

        subscription.stop();

        callback.run();
    }
}
