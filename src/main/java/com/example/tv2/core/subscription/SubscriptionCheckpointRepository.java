package com.example.tv2.core.subscription;

import java.util.Optional;

public interface SubscriptionCheckpointRepository {
    void load(String subscriptionId);

    void store(String subscriptionId, long position);
}
