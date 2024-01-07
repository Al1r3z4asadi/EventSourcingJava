package com.example.tv2.core.aggregate;


public interface Aggregate<Id> {
    Id id();
    int version();

    Object[] dequeueUncommittedEvents();
}
