package com.example.tv2.core.events;

import lombok.RequiredArgsConstructor;


public class EventMetadata{

    private String corrolationId ;
    private long causationId ;
    private String eventId;
    private long streamPosition;

    public String getCorrolationId() {
        return corrolationId;
    }

    public long getCausationId() {
        return causationId;
    }

    public String getEventId() {
        return eventId;
    }

    public long getStreamPosition() {
        return streamPosition;
    }

    public long getLogPosition() {
        return logPosition;
    }

    public String getEventType() {
        return eventType;
    }

    private long logPosition;
    private String eventType;

    public EventMetadata(){
    }
    public EventMetadata(String corrolationId){
        this.corrolationId = corrolationId ;
    }
    public EventMetadata(String correlationId , long causationId) {
        this.causationId = causationId ;
        this.corrolationId = correlationId ;
    }

    public EventMetadata(String eventId, String correlationId) {
        this.eventId = eventId ;
        this.corrolationId = correlationId ;
    }
    public EventMetadata(String corrolationId, long causationId , String eventId , long streamPosition
                    , long logPosition , String eventType) {
        this(eventId , corrolationId);
        this.causationId = causationId ;
        this.eventType = eventType ;
        this.logPosition = logPosition ;
        this.streamPosition = streamPosition ;
    }
    public EventMetadata(String eventId , long streamPosition, long logPosition , String eventType) {
        this.eventId = eventId ;
        this.eventType = eventType ;
        this.logPosition = logPosition ;
        this.streamPosition = streamPosition ;
    }
}
