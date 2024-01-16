package com.example.tv2.core.events.eventbus;

import com.example.tv2.core.events.IEvent;
import com.example.tv2.core.events.EventEnvelope;

public interface IEventBus {
    <Event extends IEvent> void publish(EventEnvelope<Event> event);
}
