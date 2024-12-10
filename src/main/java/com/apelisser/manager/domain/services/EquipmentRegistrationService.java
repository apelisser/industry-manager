package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.Equipment;

import java.util.List;

public interface EquipmentRegistrationService {

    Equipment save(Equipment equipment);

    void delete(Long equipmentId);

    Equipment findById(Long equipmentId);

    List<Equipment> findAll();

}
