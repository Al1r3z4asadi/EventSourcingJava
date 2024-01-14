package com.example.tv2.core.handlers.query;

import com.example.tv2.core.Dto.OrderDetailsDto;
import com.example.tv2.core.queries.OrderQuery;
import com.example.tv2.utils.ServiceResult;

import java.util.List;

public interface IOrderQueryHandler {
    ServiceResult<OrderDetailsDto> handle(OrderQuery.GetOrderDetailQuery query);
    ServiceResult<List<OrderDetailsDto>> handle(OrderQuery.GetAllOrderDetailsQuery query);

}
