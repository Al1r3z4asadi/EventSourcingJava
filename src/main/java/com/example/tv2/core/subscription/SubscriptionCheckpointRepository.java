package com.example.tv2.core.subscription;

import java.util.Optional;

public interface SubscriptionCheckpointRepository {
    Optional<Long> load(String subscriptionId);

    void store(String subscriptionId, long position);
}
