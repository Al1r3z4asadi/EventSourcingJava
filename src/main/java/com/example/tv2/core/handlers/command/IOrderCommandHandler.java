package com.example.tv2.core.handlers.command;

import com.example.tv2.core.commands.ICommand;
import com.example.tv2.core.commands.OrderCommand;
import com.example.tv2.utils.ServiceResult;

public interface IOrderCommandHandler {

    ServiceResult handle(OrderCommand.AddProductToOrder command) ;
    ServiceResult handle(OrderCommand.RemoveProductFromOrder command) ;


}
