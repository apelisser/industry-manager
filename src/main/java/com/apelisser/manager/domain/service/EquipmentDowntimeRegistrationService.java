package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.EquipmentDowntime;
import com.apelisser.manager.domain.model.EventTime;

import java.util.List;
import java.util.Optional;

public interface EquipmentDowntimeRegistrationService {

    EquipmentDowntime save(EquipmentDowntime equipmentDowntime);

    void delete(String equipmentDowntimeId);

    void deleteRelatedEventTime(String equipmentDowntimeId, String eventTimeId);

    EquipmentDowntime addRelatedEventsTime(String equipmentDowntimeId, List<EventTime> eventsTime);

    EquipmentDowntime findById(String equipmentDowntimeId);

    Optional<EquipmentDowntime> findFirstOverlap(EquipmentDowntime equipmentDowntime);

    List<EquipmentDowntime> findAllOverlaps(EquipmentDowntime equipmentDowntime);

    List<EquipmentDowntime> findAll();

}
