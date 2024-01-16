package com.example.tv2.service.queryhandler;

import com.example.tv2.core.Dto.OrderDetailsDto;
import com.example.tv2.core.domainservice.OrderDomainService;
import com.example.tv2.core.handlers.query.IOrderQueryHandler;
import com.example.tv2.core.queries.OrderQuery;
import com.example.tv2.report.OrderReport;
import com.example.tv2.utils.ServiceResult;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryHandler implements IOrderQueryHandler {

    @Autowired
    private final OrderReport _report ;

    public OrderQueryHandler(OrderReport report) {
        _report = report;
    }

    @Override
    public ServiceResult<OrderDetailsDto> handle(OrderQuery.GetOrderDetailQuery query) {
        RetryTemplate retryTemplate = RetryTemplate.builder()
                .retryOn(EntityNotFoundException.class)
                .exponentialBackoff(100, 2, 1000)
                .withinMillis(5000)
                .build();
            return retryTemplate.execute(context ->
                    ServiceResult.success(this._report.getOrderDetails(query.orderId())));
    }

    @Override
    public ServiceResult<List<OrderDetailsDto>> handle(OrderQuery.GetAllOrderDetailsQuery query) {
        return null;
    }
}
