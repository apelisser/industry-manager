package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.input.EquipmentDowntimeInput;
import com.apelisser.manager.api.v1.model.input.EventTimeInput;
import com.apelisser.manager.domain.entities.Employee;
import com.apelisser.manager.domain.entities.Equipment;
import com.apelisser.manager.domain.entities.EquipmentDowntime;
import com.apelisser.manager.domain.entities.Event;
import com.apelisser.manager.domain.entities.EventTime;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class EquipmentDowntimeInputDisassembler {

    public EquipmentDowntime toDomainObject(EquipmentDowntimeInput downtimeInput) {
        EquipmentDowntime downtime = getEquipmentDowntime(downtimeInput);

        List<EventTimeInput> relatedEventsInput = downtimeInput.getRelatedEvents();
        if (!CollectionUtils.isEmpty(relatedEventsInput)) {
            List<EventTime> eventTimeList = relatedEventsInput.stream()
                .map(this::toDomainObject)
                .toList();

            downtime.getRelatedEvents().addAll(eventTimeList);
        }

        return downtime;
    }

    private EquipmentDowntime getEquipmentDowntime(EquipmentDowntimeInput downtimeInput) {
        Equipment equipment = new Equipment(downtimeInput.getEquipmentId());
        Event event = new Event(downtimeInput.getEventId());
        Employee employee = new Employee(downtimeInput.getEmployeeId());

        EquipmentDowntime downtime = new EquipmentDowntime();
        downtime.setEquipment(equipment);
        downtime.setEvent(event);
        downtime.setEmployee(employee);
        downtime.setStartTime(downtimeInput.getStartTime());
        downtime.setEndTime(downtimeInput.getEndTime());
        downtime.setObservation(downtimeInput.getObservation());
        return downtime;
    }

    private EventTime toDomainObject(EventTimeInput eventTimeInput) {
        Event event = new Event(eventTimeInput.getEventId());

        EventTime eventTime = new EventTime();
        eventTime.setEvent(event);
        eventTime.setType(eventTimeInput.getType());
        eventTime.setStartTime(eventTimeInput.getStartTime());
        eventTime.setEndTime(eventTimeInput.getEndTime());
        eventTime.setObservation(eventTimeInput.getObservation());

        return eventTime;
    }

}
