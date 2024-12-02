package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.EquipmentDowntime;

public interface DatabaseDowntimeValidationService {

    void validate(EquipmentDowntime equipmentDowntime);

}
