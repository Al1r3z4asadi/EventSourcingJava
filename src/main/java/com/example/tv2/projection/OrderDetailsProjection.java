package com.example.tv2.projection;

import com.example.tv2.core.events.OrderEvent;
import com.example.tv2.core.events.eventbus.EventEnvelope;
import com.example.tv2.core.models.OrderStatus;
import com.example.tv2.projection.model.OrderDetailsView;
import com.example.tv2.projection.repositories.OrderDetailsRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderDetailsProjection extends JPAProjection<OrderDetailsView, UUID>{
    protected OrderDetailsProjection(OrderDetailsRepository repository) {
        super(repository);
    }

    @EventListener
    void handleOrderInitiated(EventEnvelope<OrderEvent.OrderInitiated> eventEnvelope) {
        add(eventEnvelope, () -> {
            var event = eventEnvelope.data();
            return new OrderDetailsView(
                    event.orderId(),
                    event.phoneNumber(),
                    OrderStatus.Pending,
                    eventEnvelope.metadata().logPosition()
            );
        });
    }
}
