package com.apelisser.manager.domain.entities;

import com.apelisser.manager.domain.models.enums.EventType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@Entity
@Table(name = "equipment_downtime")
public class EquipmentDowntime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false, updatable = false)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false, updatable = false)
    private Event event;

    @Column(nullable = false)
    private OffsetDateTime startTime;

    private OffsetDateTime endTime;

    @Setter(AccessLevel.NONE)
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @OneToMany(cascade = {PERSIST, REMOVE, DETACH}, orphanRemoval = true)
    @JoinColumn(name = "equipment_downtime_id", nullable = false)
    private final List<EventTime> relatedEvents = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Setter(AccessLevel.NONE)
    private OffsetDateTime updatedAt;

    private String observation;

    @PrePersist
    protected void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    public boolean supports(EventTime eventTime) {
        boolean startIsEqualsOrBefore = !startTime.isAfter(eventTime.getStartTime());

        if (endTime != null && eventTime.getEndTime() != null) {
            boolean endIsEqualsOrAfter = !endTime.isBefore(eventTime.getEndTime());
            return startIsEqualsOrBefore && endIsEqualsOrAfter;
        }

        return startIsEqualsOrBefore;
    }

    public boolean notSupport(EventTime other) {
        return !supports(other);
    }

    public List<EventTime> getRelatedEventsByType(EventType type) {
        if (type == null) {
            return Collections.emptyList();
        }

        return relatedEvents.stream()
            .filter(eventTime -> eventTime.typeEquals(type))
            .toList();
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
