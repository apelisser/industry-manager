package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.input.EquipmentDowntimeInput;
import com.apelisser.manager.api.v1.model.input.EventTimeInput;
import com.apelisser.manager.domain.model.Employee;
import com.apelisser.manager.domain.model.Equipment;
import com.apelisser.manager.domain.model.EquipmentDowntime;
import com.apelisser.manager.domain.model.Event;
import com.apelisser.manager.domain.model.EventTime;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class EquipmentDowntimeInputDisassembler {

    private final EventTimeInputDisassembler eventTimeInputDisassembler;

    public EquipmentDowntimeInputDisassembler(EventTimeInputDisassembler eventTimeInputDisassembler) {
        this.eventTimeInputDisassembler = eventTimeInputDisassembler;
    }

    public EquipmentDowntime toDomainObject(EquipmentDowntimeInput downtimeInput) {
        EquipmentDowntime downtime = getEquipmentDowntime(downtimeInput);

        List<EventTimeInput> relatedEventsInput = downtimeInput.getRelatedEvents();
        if (!CollectionUtils.isEmpty(relatedEventsInput)) {
            List<EventTime> eventTimeList = relatedEventsInput.stream()
                .map(eventTimeInputDisassembler::toDomain)
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

}
