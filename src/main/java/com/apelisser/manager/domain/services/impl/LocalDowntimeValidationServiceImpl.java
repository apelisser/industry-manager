package com.apelisser.manager.domain.services.impl;

import com.apelisser.manager.domain.entities.EquipmentDowntime;
import com.apelisser.manager.domain.entities.EventTime;
import com.apelisser.manager.domain.exceptions.OutOfRangeException;
import com.apelisser.manager.domain.exceptions.OverlapException;
import com.apelisser.manager.domain.services.LocalDowntimeValidationService;
import com.apelisser.manager.domain.util.Assert;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

import static com.apelisser.manager.domain.models.enums.EventType.INTERNAL;

@Service
public class LocalDowntimeValidationServiceImpl implements LocalDowntimeValidationService {

    private static final String OUT_OF_RANGE_EVENT_MESSAGE_TEMPLATE = "Event out of range: " +
        "[Event ID: %d, Type: %s, Start: %s, End: %s] exceeds primary threshold [Start: %s, End: %s].";

    private static final String OVERLAP_EVENT_MESSAGE_TEMPLATE = "Events overlap detected: " +
        "[Event ID: %d, Type: %s, Start: %s, End: %s] overlaps with [Event ID: %d, Type: %s, Start: %s, End: %s].";

    /**
     * Validates an EquipmentDowntime entity.
     *
     * <p>
     * This method takes an EquipmentDowntime entity and checks that the entity has valid properties,
     * its range is valid, the range of its related events is valid, none of the related events
     * exceed the main event, and none of the related events overlap with each other.
     * </p>
     *
     * @param equipmentDowntime the EquipmentDowntime entity to validate.
     */
    @Override
    public void validate(EquipmentDowntime equipmentDowntime) {
        // Check for null properties
        validateNullProperties(equipmentDowntime);
        equipmentDowntime.getRelatedEvents().forEach(this::validateNullProperties);

        // Check if main range is valid
        validateMainRange(equipmentDowntime);

        // Check if related events range is valid
        equipmentDowntime.getRelatedEvents().forEach(this::validateEventTimeRange);

        // Check if events exceed the main event
        validateIfEventsExceedMain(equipmentDowntime);

        // Check if any of the local internal events overlap
        List<EventTime> internalEvents = equipmentDowntime.getRelatedEventsByType(INTERNAL);
        validateIfEventsTimeHasOverlap(internalEvents);
    }

    /**
     * Validates an EventTime against an EquipmentDowntime.
     *
     * <p>
     * This method takes an EventTime and an EquipmentDowntime and checks that the EventTime
     * has valid properties, its range is valid, it fits within the EquipmentDowntime, and it
     * doesn't overlap with any of the internal events of the EquipmentDowntime.
     * </p>
     *
     * @param eventTime the EventTime to be validated.
     * @param equipmentDowntime the EquipmentDowntime to validate against.
     */
    @Override
    public void validateEventTimeForDowntime(EventTime eventTime, EquipmentDowntime equipmentDowntime) {
        // Check for null properties
        validateNullProperties(eventTime);

        // Check if event time range is valid
        validateEventTimeRange(eventTime);

        // Check eventTime fits in equipmentDowntime
        if (equipmentDowntime.notSupport(eventTime)) {
            throwOutOfRangeException(equipmentDowntime, eventTime);
        }

        // Check if eventTime overlaps with any of the internal events
        if (eventTime.typeEquals(INTERNAL)) {
            List<EventTime> internalEvents = equipmentDowntime.getRelatedEventsByType(INTERNAL);
            internalEvents.stream()
                .filter(internalEventTime -> EquipmentDowntime.hasOverlap(eventTime, internalEventTime))
                .findFirst()
                .ifPresent(internalEvent -> throwOverlapException(internalEvent, eventTime));
        }
    }

    /**
     * Validates a list of EventTime objects.
     *
     * <p>
     * This method ensures that the provided list of EventTime objects is not empty and that none of the events have
     * null properties. It also checks for any time overlaps among the events in the list.
     * </p>
     *
     * @param eventsTime the list of EventTime objects to be validated.
     * @throws IllegalArgumentException if the list of events is empty or any event has null properties.
     * @throws OverlapException if any of the events overlap with each other.
     */
    @Override
    public void validateEventsTime(List<EventTime> eventsTime) {
        Assert.notEmpty(eventsTime, "Events time list must not be empty.");

        // Check that none of the events have null properties
        eventsTime.forEach(this::validateNullProperties);

        // Filter the events to get only those of type INTERNAL and validate for overlaps
        if (eventsTime.size() > 1) {
            List<EventTime> internalEventsTime = eventsTime.stream()
                .filter(eventTime -> eventTime.typeEquals(INTERNAL))
                .toList();

            validateIfEventsTimeHasOverlap(internalEventsTime);
        }
    }

    /**
     * Verifies if any of the given EventTime objects overlap with each other.
     *
     * <p>
     * This method takes a list of EventTime objects and checks whether any of them overlap. If an overlap is found, an
     * OverlapException is thrown with a message describing the overlap.
     * </p>
     *
     * @param eventsTime the list of EventTime objects to check for overlap.
     */
    private void validateIfEventsTimeHasOverlap(List<EventTime> eventsTime) {
        if (eventsTime.size() < 2) {
            return;
        }

        List<EventTime> sortedEvents = eventsTime.stream()
            .sorted(Comparator.comparing(EventTime::getStartTime))
            .toList();

        for (int i = 0; i < sortedEvents.size() - 1; i++) {
            EventTime currentEventTime = sortedEvents.get(i);
            EventTime otherEventTime = sortedEvents.get(i + 1);
            if (EquipmentDowntime.hasOverlap(currentEventTime, otherEventTime)) {
                throwOverlapException(currentEventTime, otherEventTime);
            }
        }
    }

    /**
     * Validate that the given {@link EquipmentDowntime} and its related entities are not null.
     *
     * <p>
     * This method checks that the given {@link EquipmentDowntime} is not null and that its related entities,
     * such as equipment, event, start time, and employee, are not null either. If any of them are null, an
     * {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param equipmentDowntime the equipment downtime to validate.
     */
    private void validateNullProperties(EquipmentDowntime equipmentDowntime) {
        Assert.notNull(equipmentDowntime, "Equipment downtime must not be null.");
        Assert.notNull(equipmentDowntime.getEquipment(), "Equipment must not be null.");
        Assert.notNull(equipmentDowntime.getEvent(), "Event must not be null.");
        Assert.notNull(equipmentDowntime.getStartTime(), "Start time must not be null.");
        Assert.notNull(equipmentDowntime.getEmployee(), "Employee must not be null.");
    }

    /**
     * Validates that the properties of the event time are not null.
     *
     * <p>
     * This method throws an {@link IllegalArgumentException} if any of the
     * properties are null.
     * </p>
     *
     * @param eventTime the event time to validate.
     */
    private void validateNullProperties(EventTime eventTime) {
        Assert.notNull(eventTime, "All related events must not be null.");
        Assert.notNull(eventTime.getType(), "The type of related events must not be null.");
        Assert.notNull(eventTime.getStartTime(), "The start time of related events must not be null.");
        Assert.notNull(eventTime.getEvent(), "The event of related events must not be null.");
        Assert.notNull(eventTime.getEvent().getId(), "The event id of related events must not be null.");
    }


/**
 * Validates the main range of the equipment downtime.
 *
 * <p>
 * This method checks if the end time of the equipment downtime is after the start time.
 * If the end time is null, the method returns without any validation.
 * If the end time is not after the start time, an {@link IllegalArgumentException} is thrown.
 * </p>
 *
 * @param equipmentDowntime the equipment downtime to validate.
 */
    private void validateMainRange(EquipmentDowntime equipmentDowntime) {
        this.validateDateTimeRange(equipmentDowntime.getStartTime(), equipmentDowntime.getEndTime());
    }

    /**
     * Validates the range of an event time.
     *
     * <p>
     * This method takes an {@link EventTime} object and checks if the end time of the event
     * is after the start time. If the end time is null, the method returns without any
     * validation. If the end time is not after the start time, an
     * {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param eventTime the event time to validate.
     */
    private void validateEventTimeRange(EventTime eventTime) {
        this.validateDateTimeRange(eventTime.getStartTime(), eventTime.getEndTime());
    }

    /**
     * Validates a date time range.
     *
     * <p>
     * This method takes two {@link OffsetDateTime} objects and checks if the second one is after the first one.
     * If the second one is null, the method returns without any validation.
     * If the second one is not after the first one, an {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param start the start of the range.
     * @param end the end of the range.
     */
    private void validateDateTimeRange(OffsetDateTime start, OffsetDateTime end) {
        if (end == null) {
            return;
        }

        boolean isValidRange = end.isAfter(start);
        if (!isValidRange) {
            throw new IllegalArgumentException("End time must be after start time.");
        }

    }

    /**
     * Validate if any of the related events exceed the main event boundaries.
     *
     * <p>
     * This method checks if any of the related events exceed the main event boundaries and
     * throws an OutOfRangeException if any of them do.
     * </p>
     *
     * @param equipmentDowntime the equipment downtime to validate.
     */
    private void validateIfEventsExceedMain(EquipmentDowntime equipmentDowntime) {
        equipmentDowntime.getRelatedEvents().stream()
            .filter(equipmentDowntime::notSupport)
            .findFirst()
            .ifPresent(event -> throwOutOfRangeException(equipmentDowntime, event));
    }



    private void throwOutOfRangeException(EquipmentDowntime equipmentDowntime, EventTime event) {
        String message = String.format(OUT_OF_RANGE_EVENT_MESSAGE_TEMPLATE,
            event.getEvent().getId(),
            event.getType(),
            event.getStartTime(),
            event.getEndTime(),
            equipmentDowntime.getStartTime(),
            equipmentDowntime.getEndTime());
        throw new OutOfRangeException(message);
    }

    private void throwOverlapException(EventTime eventTime, EventTime conflictingEventTime) {
        String message = String.format(OVERLAP_EVENT_MESSAGE_TEMPLATE,
            conflictingEventTime.getEvent().getId(),
            conflictingEventTime.getType(),
            conflictingEventTime.getStartTime(),
            conflictingEventTime.getEndTime(),
            eventTime.getEvent().getId(),
            eventTime.getType(),
            eventTime.getStartTime(),
            eventTime.getEndTime());
        throw new OverlapException(message);
    }

}
