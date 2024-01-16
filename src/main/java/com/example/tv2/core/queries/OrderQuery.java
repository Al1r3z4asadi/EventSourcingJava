package com.example.tv2.core.queries;


import com.example.tv2.projection.model.IView;
import java.util.UUID;

public interface OrderQuery extends IQuery<IView> {
    record GetOrderDetailQuery(
            UUID orderId
    ) implements OrderQuery {}

    record GetAllOrderDetailsQuery() implements  OrderQuery{}

}
