package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.StateModel;
import com.apelisser.manager.domain.entity.State;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateModelAssembler {

    private final ModelMapper mapper;

    public StateModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public StateModel toModel(State state) {
        return mapper.map(state, StateModel.class);
    }

    public List<StateModel> toCollectionModel(List<State> states) {
        return states.stream()
            .map(this::toModel)
            .toList();
    }

}
