package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.EventTypeResumeModel;
import com.apelisser.manager.domain.model.EventType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventTypeResumeModelAssembler {

    private final ModelMapper mapper;

    public EventTypeResumeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EventTypeResumeModel toModel(EventType eventType) {
        return mapper.map(eventType, EventTypeResumeModel.class);
    }

    public List<EventTypeResumeModel> toCollectionModel(List<EventType> eventTypes) {
        return eventTypes.stream()
            .map(this::toModel)
            .toList();
    }

}
