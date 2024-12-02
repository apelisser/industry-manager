package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.EquipmentDowntime;
import com.apelisser.manager.domain.entities.EventTime;

import java.util.List;

public interface LocalDowntimeValidationService {

    void validate(EquipmentDowntime equipmentDowntime);

    void validateEventTimeForDowntime(EventTime eventTime, EquipmentDowntime equipmentDowntime);

    void validateEventsTime(List<EventTime> eventsTime);

}
