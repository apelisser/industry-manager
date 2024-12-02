package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.EquipmentDowntime;
import com.apelisser.manager.domain.entities.EventTime;

import java.util.List;

public interface EquipmentDowntimeRegistrationService {

    EquipmentDowntime save(EquipmentDowntime equipmentDowntime);

    void delete(Long equipmentDowntimeId);

    EquipmentDowntime addRelatedEventsTime(Long equipmentDowntimeId, List<EventTime> eventsTime);

    EquipmentDowntime findById(Long equipmentDowntimeId);

    List<EquipmentDowntime> findAll();

}
