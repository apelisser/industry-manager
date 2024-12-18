package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.input.EquipmentInput;
import com.apelisser.manager.api.v1.model.input.EquipmentUpdateInput;
import com.apelisser.manager.domain.model.Department;
import com.apelisser.manager.domain.model.Equipment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EquipmentInputDisassembler {

    private final ModelMapper mapper;

    public EquipmentInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Equipment toDomainObject(EquipmentInput equipmentInput) {
        return mapper.map(equipmentInput, Equipment.class);
    }

    public void copyToDomainObject(EquipmentInput equipmentInput, Equipment equipment) {
        equipment.setDepartment(new Department());
        equipment.setPieces(new ArrayList<>(0));
        mapper.map(equipmentInput, equipment);
    }

    public void copyToDomainObject(EquipmentUpdateInput equipmentInput, Equipment equipment) {
        equipment.setDepartment(new Department());
        mapper.map(equipmentInput, equipment);
    }

}
