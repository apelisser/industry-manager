package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.EquipmentModel;
import com.apelisser.manager.domain.entity.Equipment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipmentModelAssembler {

    private final ModelMapper mapper;

    public EquipmentModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EquipmentModel toModel(Equipment equipment) {
        return mapper.map(equipment, EquipmentModel.class);
    }

    public List<EquipmentModel> toCollectionModel(List<Equipment> equipments) {
        return equipments.stream()
            .map(this::toModel)
            .toList();
    }

}
