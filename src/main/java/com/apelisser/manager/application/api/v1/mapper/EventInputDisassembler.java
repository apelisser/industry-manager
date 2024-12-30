package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.input.EventChildrenInput;
import com.apelisser.manager.application.api.v1.model.input.EventRootInput;
import com.apelisser.manager.application.api.v1.model.input.EventUpdateInput;
import com.apelisser.manager.domain.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventInputDisassembler {

    private final ModelMapper mapper;

    public EventInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Event toDomainObject(EventRootInput eventInput) {
        return mapper.map(eventInput, Event.class);
    }

    public Event toDomainObject(EventChildrenInput eventInput) {
        return mapper.map(eventInput, Event.class);
    }

    public void copyToDomainObject(EventUpdateInput eventInput, Event event) {
        mapper.map(eventInput, event);
    }

}
