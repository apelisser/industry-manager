package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.entity.EquipmentDowntime;
import com.apelisser.manager.domain.entity.EventTime;

import java.util.List;
import java.util.Optional;

public interface EquipmentDowntimeRegistrationService {

    EquipmentDowntime save(EquipmentDowntime equipmentDowntime);

    void delete(Long equipmentDowntimeId);

    void deleteRelatedEventTime(Long equipmentDowntimeId, Long eventTimeId);

    EquipmentDowntime addRelatedEventsTime(Long equipmentDowntimeId, List<EventTime> eventsTime);

    EquipmentDowntime findById(Long equipmentDowntimeId);

    Optional<EquipmentDowntime> findFirstOverlap(EquipmentDowntime equipmentDowntime);

    List<EquipmentDowntime> findAllOverlaps(EquipmentDowntime equipmentDowntime);

    List<EquipmentDowntime> findAll();

}
