package com.example.tv2.core.events.eventbus;
import org.springframework.context.ApplicationEventPublisher;

public class EventBus implements IEventBus {

    private final ApplicationEventPublisher applicationEventPublisher;
    public EventBus(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public <Event> void publish(EventEnvelope<Event> event) {
        applicationEventPublisher.publishEvent(event);
    }

}