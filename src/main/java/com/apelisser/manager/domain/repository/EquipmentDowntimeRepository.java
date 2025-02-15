package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.EquipmentDowntime;

public interface EquipmentDowntimeRepository extends
        CustomJpaRepository<EquipmentDowntime, String>, EquipmentDowntimeRepositoryQueries {
}
