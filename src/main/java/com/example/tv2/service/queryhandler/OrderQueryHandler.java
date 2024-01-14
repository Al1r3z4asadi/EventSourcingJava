package com.example.tv2.service.queryhandler;

import com.example.tv2.core.Dto.OrderDetailsDto;
import com.example.tv2.core.handlers.query.IOrderQueryHandler;
import com.example.tv2.core.queries.OrderQuery;
import com.example.tv2.utils.ServiceResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryHandler implements IOrderQueryHandler {
    @Override
    public ServiceResult<OrderDetailsDto> handle(OrderQuery.GetOrderDetailQuery query) {
        return null;
    }

    @Override
    public ServiceResult<List<OrderDetailsDto>> handle(OrderQuery.GetAllOrderDetailsQuery query) {
        return null;
    }
}
