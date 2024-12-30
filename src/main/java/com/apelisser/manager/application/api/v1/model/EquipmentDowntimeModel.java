package com.apelisser.manager.application.api.v1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" })
public class EquipmentDowntimeModel {

    private Long id;
    private EquipmentResumeModel equipment;
    private EventResumeModel event;
    private EmployeeResumeModel employee;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private Duration duration;
    private String observation;
    private List<EventTimeModel> relatedEvents;

}
