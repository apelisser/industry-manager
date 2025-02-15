package com.apelisser.manager.domain.model;

import com.apelisser.manager.domain.enums.EventType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "event_time")
public class EventTime extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType type;

    @Column(nullable = false)
    private OffsetDateTime startTime;

    private OffsetDateTime endTime;

    @Setter(AccessLevel.NONE)
    private Duration duration;

    private String observation;

    public boolean typeEquals(EventType other) {
        return type == other;
    }

    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
        updateDuration();
    }

    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
        updateDuration();
    }

    private void updateDuration() {
        if (startTime != null && endTime != null ) {
            duration = Duration.between(startTime, endTime);
        } else {
            duration = null;
        }
    }
}
