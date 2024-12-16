package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.entity.EquipmentDowntime;

public interface EquipmentDowntimeRepository extends
        CustomJpaRepository<EquipmentDowntime, Long>, EquipmentDowntimeRepositoryQueries {
}
