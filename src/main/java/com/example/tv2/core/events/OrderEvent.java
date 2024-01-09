package com.example.tv2.core.events;

import com.example.tv2.core.models.Product;
import com.example.tv2.core.models.ProductItem;

import java.util.UUID;

public interface OrderEvent extends IEvent{


    record  OrderInitiated(UUID orderId , String phoneNumber) implements  OrderEvent{
    }

    record ProductAddedToOrder(UUID orderId , Product item) implements OrderEvent {
    }


}
