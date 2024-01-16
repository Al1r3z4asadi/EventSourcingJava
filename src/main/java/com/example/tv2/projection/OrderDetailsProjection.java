package com.example.tv2.projection;

import com.example.tv2.core.Dto.OrderDetailsDto;
import com.example.tv2.core.events.OrderEvent;
import com.example.tv2.core.events.EventEnvelope;
import com.example.tv2.core.models.OrderStatus;
import com.example.tv2.projection.model.OrderDetailsProductItem;
import com.example.tv2.projection.model.OrderDetailsView;
import com.example.tv2.projection.repositories.OrderDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
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
                    eventEnvelope.metadata().getLogPosition()
            );
        });
    }
    @EventListener
    void handleProductAddedToOrder(EventEnvelope<OrderEvent.ProductAddedToOrder> eventEnvelope) {
        getAndUpdate(eventEnvelope.data().orderId(), eventEnvelope, view -> {
            var event = eventEnvelope.data();
            var product = event.items();
            var existingProductItem = view.getProductItems().stream()
                    .filter(x -> x.getProductId().equals(product.getItems()))
                    .findFirst();

            if (existingProductItem.isEmpty()) {
                view.getProductItems().add(
                        new OrderDetailsProductItem(
                                product.getItems()
                        )
                );
            } else {
//                existingProductItem.get().increaseQuantity(productItem.quantity());
            }

            return view;
        });
    }


}
