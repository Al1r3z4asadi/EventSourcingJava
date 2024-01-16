package com.example.tv2.core.commands;

import com.example.tv2.api.models.request.OrderRequests;
import com.example.tv2.core.Dto.ProductDto;
import com.example.tv2.core.models.ProductItem;

import java.util.List;

public interface OrderCommand extends ICommand {

    record AddProductToOrder(
            String phoneNumber,
            List<ProductItem> data
    ) implements OrderCommand {}

    record  RemoveProductFromOrder(

    ) implements OrderCommand {}

}
