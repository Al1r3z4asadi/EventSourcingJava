package com.example.tv2.report;

import com.example.tv2.core.events.eventbus.EventBus;
import com.example.tv2.projection.repositories.OrderDetailsRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configs {
    @Bean
    OrderReport orderReport(OrderDetailsRepository repo) {
        return new OrderReport(repo);
    }
}
