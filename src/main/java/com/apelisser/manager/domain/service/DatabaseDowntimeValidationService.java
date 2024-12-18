package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.EquipmentDowntime;

public interface DatabaseDowntimeValidationService {

    void validate(EquipmentDowntime equipmentDowntime);

}
