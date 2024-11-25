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

    @Override
    public void validate(EquipmentDowntime equipmentDowntime) {
        // Check for null properties
        validateNullProperties(equipmentDowntime);

        // Check if main range is valid
        validateMainRange(equipmentDowntime);

        // Check if events exceed the main event
        validateIfEventsExceedMain(equipmentDowntime);

        // Check if any of the local internal events overlap
        List<EventTime> internalEvents = equipmentDowntime.getRelatedEventsByType(INTERNAL);
        validateIfLocalEventsOverlap(internalEvents);
    }

    /**
     * Checks if all the main properties of the equipment downtime are not null
     * and if all the related events also have their properties not null.
     *
     * <p>
     * This method throws an {@link IllegalArgumentException} if any of the
     * properties are null.
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

        equipmentDowntime.getRelatedEvents().forEach(eventTime -> {
            Assert.notNull(eventTime, "All related events must not be null.");
            Assert.notNull(eventTime.getType(), "The type of related events must not be null.");
            Assert.notNull(eventTime.getStartTime(), "The start time of related events must not be null.");
            Assert.notNull(eventTime.getEvent().getId(), "The event id of related events must not be null.");
        });
    }

    private void validateMainRange(EquipmentDowntime equipmentDowntime) {
        if (equipmentDowntime.getEndTime() == null) {
            return;
        }

        boolean isValidRange = equipmentDowntime.getEndTime().isAfter(equipmentDowntime.getStartTime());
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
            .ifPresent(event -> {
                String message = String.format(OUT_OF_RANGE_EVENT_MESSAGE_TEMPLATE,
                    event.getEvent().getId(),
                    event.getType(),
                    event.getStartTime(),
                    event.getEndTime(),
                    equipmentDowntime.getStartTime(),
                    equipmentDowntime.getEndTime());
                throw new OutOfRangeException(message);
            });
    }

    /**
     * Validates if any events in the list overlap.
     *
     * <p>
     * This method takes a list of event times and checks if any of them overlap. If any of them do,
     * an OverlapException is thrown.
     * </p>
     *
     * @param eventTimeModels the event times to validate.
     */
    private void validateIfLocalEventsOverlap(List<EventTime> eventTimeModels) {
        List<EventTime> sortedEvents = eventTimeModels.stream()
            .sorted(Comparator.comparing(EventTime::getStartTime))
            .toList();

        for (int i = 0; i < sortedEvents.size() - 1; i++) {
            EventTime currentEventTime = sortedEvents.get(i);
            EventTime otherEventTime = sortedEvents.get(i + 1);
            if (hasLocalOverlap(currentEventTime, otherEventTime)) {
                String message = String.format(OVERLAP_EVENT_MESSAGE_TEMPLATE,
                    currentEventTime.getEvent().getId(),
                    currentEventTime.getType(),
                    currentEventTime.getStartTime(),
                    currentEventTime.getEndTime(),
                    otherEventTime.getEvent().getId(),
                    otherEventTime.getType(),
                    otherEventTime.getStartTime(),
                    otherEventTime.getEndTime());
                throw new OverlapException(message);
            }
        }
    }

    /**
     * Determines if there is an overlap between two given EventTime objects.
     *
     * <p>
     * This method takes two EventTime objects and checks whether the time intervals of the two events overlap.
     * </p>
     *
     * @param currentEventTime the current EventTime object.
     * @param otherEventTime the other EventTime object to check for overlap.
     * @return true if the two events overlap, false otherwise.
     */
    private boolean hasLocalOverlap(EventTime currentEventTime, EventTime otherEventTime) {
        OffsetDateTime startTime = currentEventTime.getStartTime();
        OffsetDateTime endTime = currentEventTime.getEndTime();

        OffsetDateTime newStartTime = otherEventTime.getStartTime();
        OffsetDateTime newEndTime = otherEventTime.getEndTime();

        if (endTime != null && newEndTime!= null) {
            /*
            Possible scenarios:

            with:
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
