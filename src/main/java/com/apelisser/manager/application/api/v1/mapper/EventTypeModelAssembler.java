package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.EventTypeModel;
import com.apelisser.manager.domain.model.EventType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventTypeModelAssembler {

    private final ModelMapper mapper;

    public EventTypeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EventTypeModel toModel(EventType eventType) {
        return mapper.map(eventType, EventTypeModel.class);
    }

    public List<EventTypeModel> toCollectionModel(List<EventType> eventTypes) {
        return eventTypes.stream()
            .map(this::toModel)
            .toList();
    }

}
