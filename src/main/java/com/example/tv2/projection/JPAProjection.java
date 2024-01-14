package com.example.tv2.projection;


import com.example.tv2.core.events.IEvent;
import com.example.tv2.core.events.eventbus.EventEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class JPAProjection<View, Id> {
    private final CrudRepository<View, Id> repository;
    private final Logger logger = LoggerFactory.getLogger(JPAProjection.class);

    protected JPAProjection(CrudRepository<View, Id> repository) {
        this.repository = repository;
    }

    protected <Event extends IEvent> void add(EventEnvelope<Event> eventEnvelope, Supplier<View> handle) {
        var result = handle.get();

        repository.save(result);
    }

    protected <Event extends IEvent> void getAndUpdate(
            Id viewId,
            EventEnvelope<Event> eventEnvelope,
            Function<View, View> handle
    ) {
        var view = repository.findById(viewId);

        if (view.isEmpty()) {
            logger.warn("View with id %s was not found for event %s".formatted(viewId, eventEnvelope.metadata().eventType()));
            return;
        }



        var result = handle.apply(view.get());



        repository.save(result);
    }

    protected void deleteById(Id viewId) {
        repository.deleteById(viewId);
    }


}
