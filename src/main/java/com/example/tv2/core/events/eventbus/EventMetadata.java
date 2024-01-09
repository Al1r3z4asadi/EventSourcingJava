package com.example.tv2.core.events.eventbus;

public record EventMetadata(
        String eventId,
        long streamPosition,
        long logPosition,
        String eventType
) {
}
