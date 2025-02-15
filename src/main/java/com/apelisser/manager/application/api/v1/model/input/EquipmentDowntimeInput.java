package com.apelisser.manager.application.api.v1.model.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EquipmentDowntimeInput {

    private String equipmentId;
    private String eventId;
    private String employeeId;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private String observation;
    private List<EventTimeInput> relatedEvents;

}
