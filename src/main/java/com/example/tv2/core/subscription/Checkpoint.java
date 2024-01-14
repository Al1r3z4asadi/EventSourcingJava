package com.example.tv2.core.subscription;

import java.time.LocalDateTime;

public class Checkpoint {
    private final String subscriptionId;
    private final long position;
    private final LocalDateTime checkpointedAt;

    public Checkpoint(String subscriptionId, long position, LocalDateTime checkpointedAt) {
        this.subscriptionId = subscriptionId;
        this.position = position;
        this.checkpointedAt = checkpointedAt;
    }

    // Getter methods for fields

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public long getPosition() {
        return position;
    }

    public LocalDateTime getCheckpointedAt() {
        return checkpointedAt;
    }
}