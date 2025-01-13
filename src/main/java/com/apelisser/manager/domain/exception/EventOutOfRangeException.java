package com.apelisser.manager.domain.exception;

import com.apelisser.manager.domain.model.EquipmentDowntime;
import com.apelisser.manager.domain.model.EventTime;

import java.io.Serial;

public class EventOutOfRangeException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 1996060315947211739L;

    private final EquipmentDowntime parentEvent;
    private final EventTime eventExceeding;

    public EventOutOfRangeException(EquipmentDowntime parentEvent, EventTime eventExceeding, String message) {
        super(message);
        this.parentEvent = parentEvent;
        this.eventExceeding = eventExceeding;
    }

    public EventOutOfRangeException(EquipmentDowntime parentEvent, EventTime eventExceeding, String message, Throwable cause) {
        super(message, cause);
        this.parentEvent = parentEvent;
        this.eventExceeding = eventExceeding;
    }

    public EquipmentDowntime getParentEvent() {
        return parentEvent;
    }

    public EventTime getEventExceeding() {
        return eventExceeding;
    }

}
