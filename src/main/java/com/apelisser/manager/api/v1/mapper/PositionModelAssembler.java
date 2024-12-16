package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.PositionModel;
import com.apelisser.manager.domain.entity.Position;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PositionModelAssembler {

    private final ModelMapper mapper;

    public PositionModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PositionModel toModel(Position position) {
        return mapper.map(position, PositionModel.class);
    }

    public List<PositionModel> toCollectionModel(List<Position> positions) {
        return positions.stream()
            .map(this::toModel)
            .toList();
    }

}
