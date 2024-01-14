package com.example.tv2.core.config;

import com.eventstore.dbclient.EventStoreDBClient;
import com.example.tv2.core.aggregate.AggregateStore;
import com.example.tv2.core.events.OrderEvent;
import com.example.tv2.core.models.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

@Configuration
public class Appconfig {
    @Bean
    @ApplicationScope
    AggregateStore<Order, OrderEvent, UUID> shoppingCartStore(EventStoreDBClient eventStore) {
        return new AggregateStore<>(
                eventStore,
                Order::mapToStreamId,
                Order::empty
        );
    }
}
