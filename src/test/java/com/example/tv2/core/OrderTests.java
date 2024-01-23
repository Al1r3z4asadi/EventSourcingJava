package com.example.tv2.core;

import com.example.tv2.core.events.EventEnvelope;
import com.example.tv2.core.events.EventMetadata;
import com.example.tv2.core.events.OrderEvent;
import com.example.tv2.core.events.eventbus.EventBus;
import com.example.tv2.core.events.eventbus.IEventBus;
import com.example.tv2.core.models.Order;
import com.example.tv2.core.models.OrderStatus;
import com.example.tv2.core.models.Product;
import com.example.tv2.core.models.ProductItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class OrderTests {


    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private EventBus _eventBus;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void orderAggregationWorks(){
        var phoneNumber = "09129821232";
        var orderId = UUID.randomUUID();
        var goodshort = new ProductItem(UUID.randomUUID() , 1 , BigDecimal.valueOf(1999L));
        var goodshirt = new ProductItem(UUID.randomUUID() , 2 , new BigDecimal(2999L));
        Product product = new Product(List.of(goodshirt , goodshort)) ;
        var correlationId = UUID.randomUUID().toString();

        var events = new OrderEvent[]
                {
                        new OrderEvent.OrderInitiated(
                                orderId,
                                phoneNumber,
                                new EventMetadata(correlationId)
                        ),

                        new OrderEvent.ProductAddedToOrder(
                                orderId , product , new EventMetadata(correlationId)
                        )
                };

        Order order = Order.empty();
        for (var event : events) {
            order.when(event);
        }
        assertEquals(order.id(), orderId);
        assertEquals(order.getPhoneNumber(), phoneNumber);
        assertEquals(OrderStatus.Pending, order.getStatus());

        assertEquals(order.products().getItems().stream().count(), 2);

        var theshirt = order.products().getItems().get(0);
        assertEquals(goodshirt.getProductId(), theshirt.getProductId() );
    }

    @Test
    void publish_event_to_corresponding_listener(){
        var orderId = UUID.randomUUID();
        var phoneNumber = "09129821232";
        var correlationId = UUID.randomUUID().toString();

        var orderInitiatedEvent = new OrderEvent.OrderInitiated(
                orderId,
                phoneNumber,
                new EventMetadata(correlationId)
        );
        var envelope = new EventEnvelope<>(
                orderInitiatedEvent,
                new EventMetadata()
        );

        _eventBus.publish(envelope);


        ArgumentCaptor<EventEnvelope<OrderEvent.OrderInitiated>> argumentCaptor = ArgumentCaptor.forClass(EventEnvelope.class);
        verify(applicationEventPublisher).publishEvent(argumentCaptor.capture());


        OrderEvent.OrderInitiated capturedEvent = argumentCaptor.getValue().data();
        // Perform assertions on capturedEvent

    }

}
