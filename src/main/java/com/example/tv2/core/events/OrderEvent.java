package com.example.tv2.core.events;

import com.example.tv2.core.models.Product;
import com.example.tv2.core.models.ProductItem;
import jdk.jfr.Event;

import java.util.UUID;

public interface OrderEvent extends IEvent{


    record  OrderInitiated(UUID orderId , String phoneNumber , EventMetadata metadata) implements  OrderEvent{
    }

    record ProductAddedToOrder(UUID orderId , Product items , EventMetadata metadata) implements OrderEvent {
    }


}
