package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.EventResumeModel;
import com.apelisser.manager.domain.model.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventResumeModelAssembler {

    private final ModelMapper mapper;

    public EventResumeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EventResumeModel toModel(Event event) {
        return mapper.map(event, EventResumeModel.class);
    }

    public List<EventResumeModel> toCollectionModel(List<Event> events) {
        return events.stream()
            .map(this::toModel)
            .toList();
    }

}
