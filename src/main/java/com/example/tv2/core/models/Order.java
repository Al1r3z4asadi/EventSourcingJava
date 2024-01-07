package com.example.tv2.core.models;

import com.example.tv2.core.aggregate.AbstractAggregate;
import com.example.tv2.core.events.IEvent;

import java.util.UUID;

public class Order extends AbstractAggregate<IEvent, UUID> {

    private String userPhoneNumber ;

    @Override
    public void when(IEvent Event) {

    }
}