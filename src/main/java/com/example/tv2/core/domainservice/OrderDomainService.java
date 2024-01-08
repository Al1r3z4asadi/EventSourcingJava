package com.example.tv2.core.domainservice;

import com.example.tv2.core.aggregate.AggregateStore;
import com.example.tv2.core.commands.OrderCommand;
import com.example.tv2.core.events.OrderEvent;
import com.example.tv2.core.models.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderDomainService {
    private final AggregateStore<Order, OrderEvent, UUID> store;

    public OrderDomainService(AggregateStore<Order, OrderEvent, UUID> store) {
        this.store = store;
    }

    public void AddProduct(OrderCommand.AddProductToOrder command) {
//        TODO : check if exist in this microservice mini repository
        store.

    }

//    private final ProductRepository productRepository ;

}
