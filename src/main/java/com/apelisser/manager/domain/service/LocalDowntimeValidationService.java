package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.entity.EquipmentDowntime;
import com.apelisser.manager.domain.entity.EventTime;

import java.util.List;

public interface LocalDowntimeValidationService {

    void validate(EquipmentDowntime equipmentDowntime);

    void validateEventTimeForDowntime(EventTime eventTime, EquipmentDowntime equipmentDowntime);

    void validateEventsTime(List<EventTime> eventsTime);

}
