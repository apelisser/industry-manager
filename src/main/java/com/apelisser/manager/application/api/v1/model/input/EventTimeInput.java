package com.apelisser.manager.application.api.v1.model.input;

import com.apelisser.manager.domain.enums.EventType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EventTimeInput {

    private Long eventId;
    private EventType type;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private String observation;

}
