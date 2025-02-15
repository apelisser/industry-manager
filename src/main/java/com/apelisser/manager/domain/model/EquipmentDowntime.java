package com.apelisser.manager.domain.model;

import com.apelisser.manager.domain.enums.EventType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "equipment_downtime")
public class EquipmentDowntime extends BaseEntity {

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "equipment_downtime_id", nullable = false)
    private final List<EventTime> relatedEvents = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Setter(AccessLevel.NONE)
    private OffsetDateTime updatedAt;

    private String observation;

    @Override
    protected void prePersist() {
        super.prePersist();
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

    /**
     * Determines if there is an overlap between two given EventTime objects.
     *
     * <p>
     * This method takes two EventTime objects and checks whether the time intervals of the two events overlap.
     * </p>
     *
     * @param eventTime the current EventTime object.
     * @param otherEventTime the other EventTime object to check for overlap.
     * @return true if the two events overlap, false otherwise.
     */
    public static boolean hasOverlap(EventTime eventTime, EventTime otherEventTime) {
        OffsetDateTime startTime = eventTime.getStartTime();
        OffsetDateTime endTime = eventTime.getEndTime();

        OffsetDateTime otherStartTime = otherEventTime.getStartTime();
        OffsetDateTime otherEndTime = otherEventTime.getEndTime();

        if (endTime != null && otherEndTime!= null) {
            /*
            Possible scenarios:

            with:
            startTime = 4
            endTime = 7

            // collide
            otherStartTime = 3, otherEndTime = 5 :: (3 < 7 && 5 > 4) = true
            otherStartTime = 5, otherEndTime = 8 :: (5 < 7 && 8 > 4) = true
            otherStartTime = 5, otherEndTime = 6 :: (5 < 7 && 6 > 4) = true
            otherStartTime = 4, otherEndTime = 7 :: (4 < 7 && 7 > 4) = true
            otherStartTime = 3, otherEndTime = 8 :: (3 < 7 && 8 > 4) = true

            // do not collide
            otherStartTime = 0, otherEndTime = 3 :: (0 < 7 && 3 > 4) = false
            otherStartTime = 0, otherEndTime = 4 :: (0 < 7 && 4 > 4) = false
            otherStartTime = 7, otherEndTime = 8 :: (7 < 7 && 8 > 4) = false
            otherStartTime = 8, otherEndTime = 9 :: (8 < 7 && 9 > 4) = false
             */
            return otherStartTime.isBefore(endTime) && otherEndTime.isAfter(startTime);
        }
        return otherStartTime.isEqual(startTime);
    }

}
