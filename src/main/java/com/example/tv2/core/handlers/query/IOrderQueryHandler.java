package com.example.tv2.core.handlers.query;

import com.example.tv2.core.commands.OrderCommand;
import com.example.tv2.utils.ServiceResult;

public interface IOrderQueryHandler {
    ServiceResult handle(OrderCommand.AddProductToOrder command) ;

}
