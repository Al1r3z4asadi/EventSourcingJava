package com.example.tv2.core.queries;


import com.example.tv2.projection.model.IVIEW;
import java.util.UUID;

public interface OrderQuery extends IQuery<IVIEW> {
    record GetOrderDetailQuery(
            UUID orderId
    ) implements OrderQuery {}

    record GetAllOrderDetailsQuery() implements  OrderQuery{}

}
