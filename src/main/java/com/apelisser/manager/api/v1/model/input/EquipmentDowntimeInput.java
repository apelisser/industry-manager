package com.apelisser.manager.api.v1.model.input;

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

    private Long equipmentId;
    private Long eventId;
    private Long employeeId;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private String observation;
    private List<EventTimeInput> relatedEvents;

}
