package com.example.tv2.core.models;

import com.example.tv2.core.aggregate.AbstractAggregate;
import com.example.tv2.core.events.IEvent;
import com.example.tv2.core.events.OrderEvent;

import java.util.UUID;

public class Order extends AbstractAggregate<OrderEvent, UUID> {

    private UUID Id ;
    private Product products;

    public Product products() {
        return products;
    }


    public static Order empty(){
        return new Order();
    }

    @Override
    public void when(OrderEvent orderEvent) {
        if (orderEvent == null) {
            throw new IllegalArgumentException("Event cannot be null!");
        }

        if (orderEvent instanceof OrderEvent.ProductAddedToOrder) {
            OrderEvent.ProductAddedToOrder productAdded = (OrderEvent.ProductAddedToOrder) orderEvent;
            products = products.add(productAdded.item());
        } else {
            throw new IllegalArgumentException("Unsupported event type: " + orderEvent.getClass().getSimpleName());
        }
    }

    public static String mapToStreamId(UUID orderId) {
        return "Order-%s".formatted(orderId);
    }


}