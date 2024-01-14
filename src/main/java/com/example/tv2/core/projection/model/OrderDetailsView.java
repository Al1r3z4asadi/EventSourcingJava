package com.example.tv2.core.projection.model;

import com.example.tv2.core.models.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class OrderDetailsView implements IVIEW {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String phoneNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @JsonIgnore
    @Column(nullable = false)
    private long lastProcessedPosition;


    public OrderDetailsView(UUID id , String phoneNumber , OrderStatus status,
                            long lastProcessedPosition){
        this.id  = id ;
        this.phoneNumber = phoneNumber ;
        this.status = status;
        this.lastProcessedPosition = lastProcessedPosition ;
    }


    public OrderDetailsView() {

    }
}

