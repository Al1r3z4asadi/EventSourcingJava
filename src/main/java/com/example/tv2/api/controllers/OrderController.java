package com.example.tv2.api.controllers;

import com.example.tv2.api.models.MyMapper;
import com.example.tv2.api.models.request.OrderRequests;
import com.example.tv2.api.models.response.Envelope;
import com.example.tv2.core.commands.OrderCommand;
import com.example.tv2.core.handlers.command.IOrderCommandHandler;
import com.example.tv2.core.handlers.query.IOrderQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final IOrderCommandHandler _handler ;

//    private final IOrderQueryHandler _queryhandler ;

    public OrderController(IOrderCommandHandler handler
//                           IOrderQueryHandler _queryHandler
    ) {
        _handler = handler;
//        this._queryhandler =_queryHandler ;
    }

    @PostMapping("")
    public ResponseEntity<Envelope> addProduct(@RequestBody OrderRequests.AddProduct request)
    {
        if (request.productItem() == null)
            throw new IllegalArgumentException("Product Item has to be defined");

        MyMapper mapper = new MyMapper();
        var data = mapper.ToDto(request.productItem()) ;

        var command = new OrderCommand.AddProductToOrder(request.phoneNumber(), data ) ;
        var result = _handler.handle(command);

        return ResponseEntity.ok(
                Envelope.builder().timeStamp(now())
                        .message((String) (result.isSuccess() ? result.getValue().get():result.getError().get()))
                        .status(result.isSuccess() ? OK : BAD_REQUEST)
                        .statusCode(result.isSuccess() ? OK.value() : BAD_REQUEST.value())
                        .build()
        );
    }
}
