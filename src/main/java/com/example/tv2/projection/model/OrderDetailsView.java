package com.example.tv2.projection.model;

import com.example.tv2.core.models.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class OrderDetailsView implements IView {
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    private List<OrderDetailsProductItem> productItems;

    public List<OrderDetailsProductItem> getProductItems() {
        return productItems;
    }

    public UUID getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public long getLastProcessedPosition() {
        return lastProcessedPosition;
    }

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

