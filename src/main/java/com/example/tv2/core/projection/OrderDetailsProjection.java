package com.example.tv2.core.projection;

import com.example.tv2.core.events.OrderEvent;
import com.example.tv2.core.events.eventbus.EventEnvelope;
import com.example.tv2.core.models.OrderStatus;
import com.example.tv2.core.projection.model.IVIEW;
import com.example.tv2.core.projection.model.OrderDetailsView;
import com.example.tv2.core.projection.repositories.OrderDetailsRepository;
import org.springframework.context.event.EventListener;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class OrderDetailsProjection extends JPAProjection<OrderDetailsView, UUID>{
    protected OrderDetailsProjection(OrderDetailsRepository repository) {
        super(repository);
    }

    @EventListener
    void handleShoppingCartOpened(EventEnvelope<OrderEvent.OrderInitiated> eventEnvelope) {
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
