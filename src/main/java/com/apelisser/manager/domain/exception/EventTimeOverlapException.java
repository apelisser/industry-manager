package com.apelisser.manager.domain.exception;

import com.apelisser.manager.domain.model.EventTime;

import java.io.Serial;

public class EventTimeOverlapException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6078778396309432454L;

    private final EventTime eventTime;
    private final EventTime overlappingEventTime;

    public EventTimeOverlapException(EventTime eventTime, EventTime overlappingEventTime, String message) {
        super(message);
        this.eventTime = eventTime;
        this.overlappingEventTime = overlappingEventTime;
    }

    public EventTimeOverlapException(EventTime eventTime, EventTime overlappingEventTime, String message, Throwable cause) {
        super(message, cause);
        this.eventTime = eventTime;
        this.overlappingEventTime = overlappingEventTime;
    }

    public EventTime getEventTime() {
        return eventTime;
    }

    public EventTime getOverlappingEventTime() {
        return overlappingEventTime;
    }

}
