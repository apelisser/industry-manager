package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.PositionResumeModel;
import com.apelisser.manager.domain.model.Position;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PositionResumeModelAssembler {

    private final ModelMapper mapper;

    public PositionResumeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PositionResumeModel toModel(Position position) {
        return mapper.map(position, PositionResumeModel.class);
    }

    public List<PositionResumeModel> toCollectionModel(List<Position> positions) {
        return positions.stream()
            .map(this::toModel)
            .toList();
    }

}
