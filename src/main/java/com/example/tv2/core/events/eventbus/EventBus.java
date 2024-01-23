package com.example.tv2.core.events.eventbus;
import com.example.tv2.core.events.EventEnvelope;
import com.example.tv2.core.events.IEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EventBus implements IEventBus {

    private final ApplicationEventPublisher applicationEventPublisher;
    public EventBus(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public <Event extends IEvent> void publish(EventEnvelope<Event> event) {
        applicationEventPublisher.publishEvent(event);
    }

}