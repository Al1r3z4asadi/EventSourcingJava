package com.example.tv2.core.models;

import com.example.tv2.core.aggregate.AbstractAggregate;
import com.example.tv2.core.events.IEvent;
import com.example.tv2.core.events.OrderEvent;

import java.util.UUID;

public class Order extends AbstractAggregate<OrderEvent, UUID> {

    private UUID Id ;
    private  String phoneNumber ;
    private Product products;

    public Order(
            UUID id,
            String phoneNumber
    ) {
        this.id = id;
        this.phoneNumber = phoneNumber;

        enqueue(new OrderEvent.OrderInitiated(id, phoneNumber));
    }
    public Product products() {
        return products;
    }

    public void addProductItem(Product productItems) {
           enqueue(new OrderEvent.ProductAddedToOrder(
                id,
                productItems
        ));
    }

    private Order(){

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