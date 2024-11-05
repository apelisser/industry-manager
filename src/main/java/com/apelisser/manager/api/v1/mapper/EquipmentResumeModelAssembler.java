package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.EquipmentResumeModel;
import com.apelisser.manager.domain.entities.Equipment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipmentResumeModelAssembler {

    private final ModelMapper mapper;

    public EquipmentResumeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EquipmentResumeModel toModel(Equipment equipment) {
        return mapper.map(equipment, EquipmentResumeModel.class);
    }

    public List<EquipmentResumeModel> toCollectionModel(List<Equipment> equipments) {
        return equipments.stream()
            .map(this::toModel)
            .toList();
    }

}
