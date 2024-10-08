package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.EventReasonResumeModel;
import com.apelisser.manager.domain.model.EventReason;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventReasonResumeModelAssembler {

    private final ModelMapper mapper;

    public EventReasonResumeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EventReasonResumeModel toModel(EventReason eventReason) {
        return mapper.map(eventReason, EventReasonResumeModel.class);
    }

    public List<EventReasonResumeModel> toCollectionModel(List<EventReason> eventReasons) {
        return eventReasons.stream()
            .map(this::toModel)
            .toList();
    }

}
