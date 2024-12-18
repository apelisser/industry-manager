package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.EquipmentDowntime;
import com.apelisser.manager.domain.model.EventTime;

import java.util.List;

public interface LocalDowntimeValidationService {

    void validate(EquipmentDowntime equipmentDowntime);

    void validateEventTimeForDowntime(EventTime eventTime, EquipmentDowntime equipmentDowntime);

    void validateEventsTime(List<EventTime> eventsTime);

}
