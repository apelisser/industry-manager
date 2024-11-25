package com.apelisser.manager.domain.services.impl;

import com.apelisser.manager.domain.entities.EquipmentDowntime;
import com.apelisser.manager.domain.services.DatabaseDowntimeValidationService;
import org.springframework.stereotype.Service;

@Service
public class DatabaseDowntimeValidationServiceImpl implements DatabaseDowntimeValidationService {

    @Override
    public void validate(EquipmentDowntime equipmentDowntime) {
        // Check if main event overlaps with any database event
        if (hasOverlap(equipmentDowntime)) {
            // TODO
            throw new IllegalArgumentException();
        }
    }

    private boolean hasOverlap(EquipmentDowntime equipmentDowntime) {
        // TODO - verificar se o evento informado sobrepõe algum outro já registrado
        return false;
    }

}
