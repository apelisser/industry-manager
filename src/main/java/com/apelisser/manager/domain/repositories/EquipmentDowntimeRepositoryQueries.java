package com.apelisser.manager.domain.repositories;

import com.apelisser.manager.domain.entities.EquipmentDowntime;

import java.util.List;
import java.util.Optional;

public interface EquipmentDowntimeRepositoryQueries {

    Optional<EquipmentDowntime> findFirstOverlap(EquipmentDowntime equipmentDowntime);

    List<EquipmentDowntime> findAllOverlaps(EquipmentDowntime equipmentDowntime);

}
