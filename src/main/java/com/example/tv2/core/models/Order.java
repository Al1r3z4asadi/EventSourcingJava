package com.example.tv2.core.models;

import com.example.tv2.core.Exceptions.ErrorCodes;
import com.example.tv2.core.Exceptions.OrderException;
import com.example.tv2.core.aggregate.AbstractAggregate;
import com.example.tv2.core.events.EventMetadata;
import com.example.tv2.core.events.OrderEvent;

import java.util.UUID;

public class Order extends AbstractAggregate<OrderEvent, UUID> {

    private UUID Id ;
    private  String phoneNumber ;
    private Product products;

    private OrderStatus status;

    public static Order Initiate(UUID orderId, String phoneNumber, String correlationId) {
        return new Order(orderId , phoneNumber , correlationId);
    }

    OrderStatus status() {
        return status;
    }

    public Order(
            UUID id,
            String phoneNumber ,
            String correlationId
    ) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        EventMetadata metadata = new EventMetadata(correlationId) ;
        enqueue(new OrderEvent.OrderInitiated(id, phoneNumber ,metadata));
    }
    public Product products() {
        return products;
    }

    public void addProductItem(Product productItems , String correlationId , long causeationId) {
        if (productItems == null)
            throw new OrderException(ErrorCodes.PRODUCT_NOT_FOUND.getMessage() ,
                                     ErrorCodes.PRODUCT_NOT_FOUND.getCode());
        EventMetadata metadata = new EventMetadata(correlationId ,causeationId) ;
        enqueue(new OrderEvent.ProductAddedToOrder(
                id,
                productItems , metadata
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
            throw new OrderException(ErrorCodes.CAN_NOT_APPLY_TO_EMPTY_EVENT.getMessage(),
                                    ErrorCodes.CAN_NOT_APPLY_TO_EMPTY_EVENT.getCode());
        }

        if (orderEvent instanceof OrderEvent.ProductAddedToOrder) {
            OrderEvent.ProductAddedToOrder productAdded = (OrderEvent.ProductAddedToOrder) orderEvent;
            products = products.add(productAdded.items());
        }
        else if(orderEvent instanceof OrderEvent.OrderInitiated){
            id = ((OrderEvent.OrderInitiated) orderEvent).orderId();
            phoneNumber = ((OrderEvent.OrderInitiated) orderEvent).phoneNumber();
            products = products.empty() ;
            status = OrderStatus.Pending;
        }
        else {
            throw new OrderException(ErrorCodes.UNSUPPORTED_EVENT.getMessage() + orderEvent.getClass().getSimpleName(),
                                    ErrorCodes.UNSUPPORTED_EVENT.getCode());
        }
    }

    public static String mapToStreamId(UUID orderId) {
        return "Order-%s".formatted(orderId);
    }


}