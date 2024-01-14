package com.example.tv2.core.subscription;


import com.eventstore.dbclient.SubscribeToAllOptions;
import com.eventstore.dbclient.SubscriptionFilter;

public class EventStoreDBSubscriptionToAllOptions {

    private final String subscriptionId;
    private final boolean ignoreDeserializationErrors;
    private final SubscribeToAllOptions subscribeToAllOptions;

    public EventStoreDBSubscriptionToAllOptions(String subscriptionId, boolean ignoreDeserializationErrors,
                                                SubscribeToAllOptions subscribeToAllOptions) {
        this.subscriptionId = subscriptionId;
        this.ignoreDeserializationErrors = ignoreDeserializationErrors;
        this.subscribeToAllOptions = subscribeToAllOptions;
    }

    public static EventStoreDBSubscriptionToAllOptions getDefault() {
        SubscriptionFilter filterOutSystemEvents = SubscriptionFilter.newBuilder()
                .withEventTypeRegularExpression("^[^\\$].*")
                .build();

        SubscribeToAllOptions options = SubscribeToAllOptions.get()
                .fromStart()
                .filter(filterOutSystemEvents);

        return new EventStoreDBSubscriptionToAllOptions("default", true, options);
    }

    // Getter methods for fields

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public boolean isIgnoreDeserializationErrors() {
        return ignoreDeserializationErrors;
    }

    public SubscribeToAllOptions getSubscribeToAllOptions() {
        return subscribeToAllOptions;
    }
}