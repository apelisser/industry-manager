package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.EquipmentDowntime;

import java.util.List;

public interface EquipmentDowntimeRegistrationService {

    EquipmentDowntime save(EquipmentDowntime equipmentDowntime);

    void delete(Long equipmentDowntimeId);

    EquipmentDowntime findById(Long equipmentDowntimeId);

    List<EquipmentDowntime> findAll();

}
