package com.example.tv2.core.domainservice;

import com.example.tv2.core.Dto.OrderDetailsDto;
import com.example.tv2.core.aggregate.AggregateStore;
import com.example.tv2.core.commands.OrderCommand;
import com.example.tv2.core.events.OrderEvent;
import com.example.tv2.core.models.Order;
import com.example.tv2.core.models.Product;
import com.example.tv2.core.models.ProductItem;
import com.example.tv2.projection.repositories.OrderDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderDomainService {
    private final AggregateStore<Order, OrderEvent, UUID> store;


    public OrderDomainService(AggregateStore<Order, OrderEvent, UUID> store ) {
        this.store = store;
    }

    private long initiateOrder(UUID orderId , String phoneNumber , String correlationId){
        var order = Order.Initiate(orderId , phoneNumber , correlationId);
        return store.add(order);
    }

    public void AddProduct(OrderCommand.AddProductToOrder command) {
//        TODO : check if exist in this microservice mini repository
        var orderId = UUID.randomUUID();
        String correlationId = UUID.randomUUID().toString();
        long causeationId = initiateOrder(orderId , command.phoneNumber() , correlationId);
        store.getAndUpdate(
                current -> current.addProductItem(new Product(command.data()) ,correlationId , causeationId),
                orderId,
                0

        );
    }



//    private final ProductRepository productRepository ;

}
