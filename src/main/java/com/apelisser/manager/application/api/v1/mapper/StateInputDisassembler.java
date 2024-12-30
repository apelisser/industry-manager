package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.input.StateInput;
import com.apelisser.manager.domain.model.Country;
import com.apelisser.manager.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StateInputDisassembler {

    private final ModelMapper mapper;

    public StateInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public State toDomainObject(StateInput stateInput) {
        return mapper.map(stateInput, State.class);
    }

    public void copyToDomainObject(StateInput stateInput, State state) {
        state.setCountry(new Country());
        mapper.map(stateInput, state);
    }
}
