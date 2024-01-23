package com.example.tv2.core;

import com.example.tv2.core.events.EventEnvelope;
import com.example.tv2.core.events.EventMetadata;
import com.example.tv2.core.events.OrderEvent;
import com.example.tv2.core.events.eventbus.EventBus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.ApplicationEventPublisher;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EventBustTest {

    @Test
    void testEventBusPublish() {
        // Arrange
        ApplicationEventPublisher applicationEventPublisher = mock(ApplicationEventPublisher.class);
        EventBus eventBus = new EventBus(applicationEventPublisher);

        // Create an example event
        var orderId = UUID.randomUUID();
        var phoneNumber = "09129821232";
        var correlationId = UUID.randomUUID().toString();

        var orderInitiatedEvent = new OrderEvent.OrderInitiated(
                orderId,
                phoneNumber,
                new EventMetadata(correlationId)
        );

        var eventEnvelope = new EventEnvelope<>(
                orderInitiatedEvent,
                new EventMetadata()
        );

        // Act
        eventBus.publish(eventEnvelope);

        // Assert
        // Verify that publishEvent was called with the correct argument
        ArgumentCaptor<EventEnvelope<OrderEvent.OrderInitiated>> argumentCaptor =
                ArgumentCaptor.forClass(EventEnvelope.class);
        verify(applicationEventPublisher).publishEvent(argumentCaptor.capture());

        // Perform additional assertions if needed
        // For example, you might want to check the captured value
        OrderEvent.OrderInitiated capturedEvent = argumentCaptor.getValue().data();
        // Perform assertions on capturedEvent
    }
}
