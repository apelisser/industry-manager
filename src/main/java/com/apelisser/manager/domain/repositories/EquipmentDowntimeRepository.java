package com.apelisser.manager.domain.repositories;

import com.apelisser.manager.domain.entities.EquipmentDowntime;

public interface EquipmentDowntimeRepository extends
        CustomJpaRepository<EquipmentDowntime, Long>, EquipmentDowntimeRepositoryQueries {
}
