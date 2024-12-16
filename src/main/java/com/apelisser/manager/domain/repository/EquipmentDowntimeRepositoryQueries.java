package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.entity.EquipmentDowntime;

import java.util.List;
import java.util.Optional;

public interface EquipmentDowntimeRepositoryQueries {

    Optional<EquipmentDowntime> findFirstOverlap(EquipmentDowntime equipmentDowntime);

    List<EquipmentDowntime> findAllOverlaps(EquipmentDowntime equipmentDowntime);

}
