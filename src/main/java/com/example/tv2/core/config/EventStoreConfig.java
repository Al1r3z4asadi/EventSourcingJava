package com.example.tv2.core.config;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import com.example.tv2.agent.ESDBSubscriptionBackground;
import com.example.tv2.core.events.eventbus.EventBus;
import com.example.tv2.core.subscription.ESSubscriptionCheckpointRepository;
import com.example.tv2.core.subscription.SubscriptionCheckpointRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EventStoreConfig {
    @Bean
    @Scope("singleton")
    EventStoreDBClient eventStoreDBClient(@Value("${esdb.connectionstring}") String connectionString) {
        try {
            EventStoreDBClientSettings settings = EventStoreDBConnectionString.parse(connectionString);

            return EventStoreDBClient.create(settings);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    EventBus eventBus(ApplicationEventPublisher applicationEventPublisher) {
        return new EventBus(applicationEventPublisher);
    }

    @Bean
    SubscriptionCheckpointRepository subscriptionCheckpointRepository(EventStoreDBClient eventStore) {
        return new ESSubscriptionCheckpointRepository(eventStore);
    }

    @Bean
    ESDBSubscriptionBackground eventStoreDBSubscriptionBackground(
            EventStoreDBClient eventStore,
            SubscriptionCheckpointRepository subscriptionCheckpointRepository,
            EventBus eventBus
    ) {
        return new ESDBSubscriptionBackground(eventStore, subscriptionCheckpointRepository, eventBus);
    }
}
