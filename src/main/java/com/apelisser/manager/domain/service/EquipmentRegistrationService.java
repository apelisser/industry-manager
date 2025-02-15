package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Equipment;

import java.util.List;

public interface EquipmentRegistrationService {

    Equipment save(Equipment equipment);

    void delete(String equipmentId);

    Equipment findById(String equipmentId);

    List<Equipment> findAll();

}
