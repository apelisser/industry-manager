package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.EquipmentDowntimeModel;
import com.apelisser.manager.domain.entities.EquipmentDowntime;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EquipmentDowntimeModelAssembler {

    private final ModelMapper mapper;

    public EquipmentDowntimeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public EquipmentDowntimeModel toModel(EquipmentDowntime equipmentDowntime) {
        return mapper.map(equipmentDowntime, EquipmentDowntimeModel.class);
    }

    public List<EquipmentDowntimeModel> toCollectionModel(List<EquipmentDowntime> equipmentDowntimes) {
        return equipmentDowntimes.stream()
            .map(this::toModel)
            .toList();
    }

}
