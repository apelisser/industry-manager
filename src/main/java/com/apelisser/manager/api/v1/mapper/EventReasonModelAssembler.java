package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.EventReasonModel;
import com.apelisser.manager.domain.model.EventReason;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventReasonModelAssembler {

    private final ModelMapper mapper;

    public EventReasonModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EventReasonModel toModel(EventReason eventReason) {
        return mapper.map(eventReason, EventReasonModel.class);
    }

    public List<EventReasonModel> toCollectionModel(List<EventReason> eventReasons) {
        return eventReasons.stream()
            .map(this::toModel)
            .toList();
    }

}
