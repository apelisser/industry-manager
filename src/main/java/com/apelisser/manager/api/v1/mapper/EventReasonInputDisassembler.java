package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.input.EventReasonInput;
import com.apelisser.manager.domain.model.EventReason;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventReasonInputDisassembler {

    private final ModelMapper mapper;

    public EventReasonInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EventReason toDomainObject(EventReasonInput eventReasonInput) {
        return mapper.map(eventReasonInput, EventReason.class);
    }

}
