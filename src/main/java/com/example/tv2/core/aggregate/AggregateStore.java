package com.example.tv2.core.aggregate;


import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.ReadResult;
import com.eventstore.dbclient.StreamNotFoundException;
import com.example.tv2.core.serialization.EventSerializer;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;


public class AggregateStore<Entity extends AbstractAggregate<Event, Id>, Event, Id> {
    private final EventStoreDBClient eventStore;
    private final Function<Id, String> mapToStreamId;
    private final Supplier<Entity> getEmpty;

    public AggregateStore(EventStoreDBClient eventStore,Function<Id, String> mapToStreamId,
                          Supplier<Entity> getEmpty
    )
    {

        this.eventStore = eventStore;
        this.mapToStreamId = mapToStreamId;
        this.getEmpty = getEmpty;
    }

    private Optional<List<Event>> getEvents(String streamId) {
        ReadResult result;
        try {
            result = eventStore.readStream(streamId).get();
        } catch (Throwable e) {
            Throwable innerException = e.getCause();

            if (innerException instanceof StreamNotFoundException) {
                return Optional.empty();
            }
            throw new RuntimeException(e);
        }

        var events = result.getEvents().stream()
                .map(EventSerializer::<Event>deserialize)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        return Optional.of(events);
    }


}
