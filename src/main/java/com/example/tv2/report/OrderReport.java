package com.example.tv2.report;

import com.example.tv2.core.Dto.OrderDetailsDto;
import com.example.tv2.projection.repositories.OrderDetailsRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class OrderReport {

    private final OrderDetailsRepository detailsRepository;

    public OrderReport(OrderDetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
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
}
