package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.input.EventTimeInput;
import com.apelisser.manager.domain.entity.Event;
import com.apelisser.manager.domain.entity.EventTime;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventTimeInputDisassembler {

    public EventTime toDomain(EventTimeInput eventTimeInput) {
        Event event = new Event(eventTimeInput.getEventId());

        EventTime eventTime = new EventTime();
        eventTime.setEvent(event);
        eventTime.setType(eventTimeInput.getType());
        eventTime.setStartTime(eventTimeInput.getStartTime());
        eventTime.setEndTime(eventTimeInput.getEndTime());
        eventTime.setObservation(eventTimeInput.getObservation());

        return eventTime;
    }

    public List<EventTime> toCollectionDomain(List<EventTimeInput> eventsTimeInput) {
        return eventsTimeInput.stream()
            .map(this::toDomain)
            .toList();
    }

}
