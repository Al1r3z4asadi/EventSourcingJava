package com.example.tv2.core.events.eventbus;

import com.example.tv2.core.events.eventbus.EventEnvelope;

public interface IEventBus {
    <Event> void publish(EventEnvelope<Event> event);
}
