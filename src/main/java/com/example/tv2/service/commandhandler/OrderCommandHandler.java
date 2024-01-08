package com.example.tv2.service.commandhandler;

import com.example.tv2.core.commands.OrderCommand;
import com.example.tv2.core.domainservice.OrderDomainService;
import com.example.tv2.core.handlers.command.IOrderCommandHandler;
import com.example.tv2.utils.ServiceResult;
import org.springframework.stereotype.Service;


@Service
public class OrderCommandHandler implements IOrderCommandHandler {

    private final OrderDomainService _orderdomainService ;

    public OrderCommandHandler(OrderDomainService orderdomainService) {
        _orderdomainService = orderdomainService;
    }

    @Override
    public ServiceResult handle(OrderCommand.AddProductToOrder command) {

        _orderdomainService.AddProduct(command) ;
        return ServiceResult.failure("error");
    }

    @Override
    public ServiceResult handle(OrderCommand.RemoveProductFromOrder command) {
        return ServiceResult.failure("error");
    }


}
