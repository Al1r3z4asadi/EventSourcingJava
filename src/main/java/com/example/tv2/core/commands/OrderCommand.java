package com.example.tv2.core.commands;

import com.example.tv2.core.Dto.ProductDto;

import java.util.List;

public interface OrderCommand extends ICommand {

    record AddProductToOrder(
            String phoneNumber,
            List<ProductDto> data
    ) implements OrderCommand {}

    record  RemoveProductFromOrder(

    ) implements OrderCommand {}

}
