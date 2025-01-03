package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.EventModel;
import com.apelisser.manager.domain.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventModelAssembler {

    private final ModelMapper mapper;

    public EventModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EventModel toModel(Event event) {
        return mapper.map(event, EventModel.class);
    }

}
