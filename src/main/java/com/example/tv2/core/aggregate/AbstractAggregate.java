package com.example.tv2.core.aggregate;


import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public abstract class AbstractAggregate<Event, Id> implements Aggregate<Id> {
    protected Id id;
    protected int version;

    private final Queue uncommittedEvents = new LinkedList<>();

    public Id id() {
        return id;
    }

    public int version() {
        return version;
    }

    public Object[] dequeueUncommittedEvents() {
        var dequeuedEvents = uncommittedEvents.toArray();

        uncommittedEvents.clear();

        return dequeuedEvents;
    }

    public abstract void when(Event event);

    protected UUID enqueue(Event event) {
        uncommittedEvents.add(event);
        when(event);
//        version++;
//        no versioning for now
        return UUID.randomUUID();
    }



}
