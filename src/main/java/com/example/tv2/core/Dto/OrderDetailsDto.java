package com.example.tv2.core.Dto;

import com.example.tv2.core.models.OrderStatus;
import lombok.Data;

@Data
public class OrderDetailsDto {

    private String phoneNumber ;
    private OrderStatus status ;

}
