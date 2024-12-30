package com.apelisser.manager.application.api.v1.model;

import com.apelisser.manager.domain.enums.EventType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" })
public class EventTimeModel {

    private Long id;
    private EventResumeModel event;
    private EventType type;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private Duration duration;
    private String observation;

}
