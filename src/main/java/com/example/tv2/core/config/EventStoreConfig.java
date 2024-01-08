package com.example.tv2.core.config;

import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import org.springframework.beans.factory.annotation.Value;
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
}
