package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.input.PositionInput;
import com.apelisser.manager.domain.model.Company;
import com.apelisser.manager.domain.model.Position;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PositionInputDisassembler {

    private final ModelMapper mapper;

    public PositionInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Position toDomainObject(PositionInput positionInput) {
        return mapper.map(positionInput, Position.class);
    }

    public void copyToDomainObject(PositionInput positionInput, Position position) {
        position.setCompany(new Company());
        position.setSuperior(new Position());
        mapper.map(positionInput, position);
    }

}
