package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.entity.EquipmentDowntime;

public interface DatabaseDowntimeValidationService {

    void validate(EquipmentDowntime equipmentDowntime);

}
