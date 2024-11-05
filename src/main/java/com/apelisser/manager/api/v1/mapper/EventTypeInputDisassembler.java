package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.input.EventTypeInput;
import com.apelisser.manager.api.v1.model.input.EventTypeUpdateInput;
import com.apelisser.manager.domain.entities.EventType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventTypeInputDisassembler {

    private final ModelMapper mapper;

    public EventTypeInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EventType toDomainObject(EventTypeInput eventTypeInput) {
        return mapper.map(eventTypeInput, EventType.class);
    }

    public void copyToDomainObject(EventTypeUpdateInput eventTypeInput, EventType eventType) {
        mapper.map(eventTypeInput, eventType);
    }

}
