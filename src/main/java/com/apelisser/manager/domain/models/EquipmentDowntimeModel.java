package com.apelisser.manager.domain.models;

import com.apelisser.manager.domain.util.Assert;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a downtime period of equipment.
 * It contains the equipment data, the start and end date of the downtime, the employee responsible for the downtime
 * and lists of internal and parallel events.
 */
public class EquipmentDowntimeModel {

    @Getter
    private final EquipmentModel equipment;
    private final EventTimeModel eventTime;
    @Getter
    private Long employeeId;
    private final List<EventTimeModel> internalEvents = new LinkedList<>();
    private final List<EventTimeModel> parallelEvents = new LinkedList<>();
    @Setter
    @Getter
    private String observation;

    public EquipmentDowntimeModel(Long equipmentId, Long eventId, OffsetDateTime startTime, Long employeeId) {
        this.equipment = new EquipmentModel(equipmentId);
        this.eventTime = new EventTimeModel(eventId, startTime);
        this.employeeId = employeeId;
    }

    public EquipmentDowntimeModel(EquipmentModel equipment, EventTimeModel eventTime, Long employeeId) {
        Assert.notNull(equipment, "Equipment must not be null.");
        Assert.notNull(eventTime, "EventTime must not be null.");
        Assert.notNull(employeeId, "Employee id must not be null.");
        this.equipment = equipment;
        this.eventTime = eventTime;
        this.employeeId = employeeId;
    }

    /**
     * Adds a new internal event to the list of internal events if it is valid.
     *
     * <p>
     * The new event time must not be null and must fit within the main event time.
     * If the new event time does not fit within the main event time, an
     * IllegalArgumentException is thrown. Additionally, the new event time should
     * not overlap with any existing internal events. If an overlap is detected, an
     * IllegalArgumentException is thrown.
     * <p/>
     *
     * @param newEventTime the new EventTimeModel to be added.
     * @throws IllegalArgumentException if the new event time does not fit within
     *                                  the main event time or overlaps with any
     *                                  existing internal events.
     */
    public void addInternalEvent(EventTimeModel newEventTime) {
        Assert.notNull(newEventTime, "EventTime must not be null.");

        if (eventTime.notSupport(newEventTime)) {
            throw new IllegalArgumentException("New EventTime does not fit in main event.");
        }

        internalEvents.forEach(internalEvent -> {
            boolean isOverlapping = hasOverlap(internalEvent, newEventTime);
            if (isOverlapping) {
                throw new IllegalArgumentException("The new EventTime overlaps with the internal event.");
            }
        });

        internalEvents.add(newEventTime);
    }

    /**
     * Adds a new parallel event to the list of parallel events if it is valid.
     *
     * <p>
     * The new event time must not be null and must fit within the main event time.
     * If the new event time does not fit within the main event time, an
     * IllegalArgumentException is thrown.
     * <p/>
     *
     * @param newEventTime the new EventTimeModel to be added.
     * @throws IllegalArgumentException if the new event time does not fit within
     *                                  the main event time.
     */
    public void addParallelEvent(EventTimeModel newEventTime) {
        Assert.notNull(newEventTime, "EventTime must not be null.");

        if (eventTime.notSupport(newEventTime)) {
            throw new IllegalArgumentException("New EventTime does not fit in main event.");
        }

        parallelEvents.add(newEventTime);
    }

    /**
     * Retrieves an unmodifiable list of internal events.
     *
     * <p>
     * The list contains the internal events associated with this equipment downtime.
     * These events are intended to be non-overlapping and fall within the main event time.
     * <p/>
     *
     * @return an unmodifiable list of {@link EventTimeModel} representing internal events.
     */
    public List<EventTimeModel> getInternalEvents() {
        return Collections.unmodifiableList(internalEvents);
    }

    /**
     * Retrieves an unmodifiable list of parallel events.
     *
     * <p>
     * The list contains the parallel events associated with this equipment downtime.
     * These events are intended to be non-overlapping and fall within the main event time.
     * <p/>
     *
     * @return an unmodifiable list of {@link EventTimeModel} representing parallel events.
     */
    public List<EventTimeModel> getParallelEvents() {
        return Collections.unmodifiableList(parallelEvents);
    }

    /**
     * Changes the employee associated with this equipment downtime.
     *
     * <p>
     * The id of the new employee must not be null.
     * <p/>
     *
     * @param employeeId the id of the new employee.
     */
    public void changeEmployee(Long employeeId) {
        Assert.notNull(employeeId, "Employee id must not be null.");
        this.employeeId = employeeId;
    }

    /**
     * Returns true if the two given EventTimeModel have some overlap in time, false otherwise.
     * <p>
     * The logic is as follows:
     * <ul>
     *     <li>If both EventTimeModel have endtime, then it is considered an overlap if the new event starts before the current ends and the new event ends after the current starts.</li>
     *     <li>If the new event has no endtime, then it is considered an overlap if the new event starts at the same time as the current event.</li>
     * </ul>
     * <p/>
     *
     * @param currentEventTime the current EventTimeModel.
     * @param newEventTime the new EventTimeModel to be checked.
     * @return true if the two events overlap, false otherwise.
     */
    private boolean hasOverlap(EventTimeModel currentEventTime, EventTimeModel newEventTime) {
        OffsetDateTime startTime = currentEventTime.getStartTime();
        OffsetDateTime endTime = currentEventTime.getEndTime().orElse(null);

        OffsetDateTime newStartTime = newEventTime.getStartTime();
        OffsetDateTime newEndTime = newEventTime.getEndTime().orElse(null);

        if (endTime != null && newEndTime!= null) {
            /*
            startTime = 4
            endTime = 7

            // collide
            newStartTime = 3, newEndTime = 5 :: (3 < 7 && 5 > 4) = true
            newStartTime = 5, newEndTime = 8 :: (5 < 7 && 8 > 4) = true
            newStartTime = 5, newEndTime = 6 :: (5 < 7 && 6 > 4) = true
            newStartTime = 4, newEndTime = 7 :: (4 < 7 && 7 > 4) = true
            newStartTime = 3, newEndTime = 8 :: (3 < 7 && 8 > 4) = true

            // do not collide
            newStartTime = 0, newEndTime = 3 :: (0 < 7 && 3 > 4) = false
            newStartTime = 0, newEndTime = 4 :: (0 < 7 && 4 > 4) = false
            newStartTime = 7, newEndTime = 8 :: (7 < 7 && 8 > 4) = false
            newStartTime = 8, newEndTime = 9 :: (8 < 7 && 9 > 4) = false
             */
            return newStartTime.isBefore(endTime) && newEndTime.isAfter(startTime);
        }
        return newStartTime.isEqual(startTime);
    }

}
