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
    private final OrderDetailsRepository detailsRepository;


    public OrderDomainService(AggregateStore<Order, OrderEvent, UUID> store ,
                              OrderDetailsRepository detailsRepository) {
        this.store = store;
        this.detailsRepository = detailsRepository ;
    }

    private void initiateOrder(UUID orderId , String phoneNumber){
        store.add(new Order(orderId , phoneNumber));
    }


    public void AddProduct(OrderCommand.AddProductToOrder command) {
//        TODO : check if exist in this microservice mini repository
        var orderId = UUID.randomUUID();
        initiateOrder(orderId , command.phoneNumber());

        List<ProductItem> items = command.data().stream()
                .map(dto -> new ProductItem(UUID.fromString(dto.getProductId()), dto.getCount() , dto.getPrice()))
                .collect(Collectors.toList());

        store.getAndUpdate(
                current -> current.addProductItem(new Product(items)),
                orderId,
                0
        );

    }

    public OrderDetailsDto getOrderDetails(UUID uuid) {
        var result = detailsRepository.findById(uuid);
        if(result.isEmpty()){
            throw new EntityNotFoundException("Shopping cart not found");
        }
        var data =  result.get();
        OrderDetailsDto dto = new OrderDetailsDto();
        dto.setPhoneNumber(data.getPhoneNumber());
        dto.setStatus(data.getStatus());
        return dto ;
    }

//    private final ProductRepository productRepository ;

}
