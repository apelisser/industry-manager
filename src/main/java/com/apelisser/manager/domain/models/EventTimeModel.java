package com.apelisser.manager.domain.models;

import com.apelisser.manager.domain.util.Assert;

import java.time.OffsetDateTime;
import java.util.Optional;

public class EventTimeModel {

    private final OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private final Long eventId;
    private String observation;

    public EventTimeModel (Long eventId, OffsetDateTime startTime) {
        Assert.notNull(eventId, "Event id must not be null.");
        Assert.notNull(startTime, "Event start time must not be null.");
        this.eventId = eventId;
        this.startTime = startTime;
    }

    /**
     * Indicates whether the given {@link EventTimeModel} is supported by this event time.
     *
     * <p>
     * The given event time is supported if its start time is equal or before the start time of this event time.
     * If the given event time has an end time and the end time of this event time is not null, the end time of this
     * event time must be equal or after the end time of the given event time.
     *
     * @param other the other event time to check.
     * @return true if the given event time is supported, false otherwise.
     */
    public boolean supports(EventTimeModel other) {
        Assert.notNull(other, "Event time must not be null.");
        boolean startIsEqualsOrBefore = !startTime.isAfter(other.startTime);

        if (endTime != null && other.endTime != null) {
            boolean endIsEqualsOrAfter = !endTime.isBefore(other.endTime);
            return startIsEqualsOrBefore && endIsEqualsOrAfter;
        }

        return startIsEqualsOrBefore;
    }

    /**
     * Indicates whether the given {@link EventTimeModel} is not supported by this event time.
     *
     * <p>
     * The given event time is not supported if its start time is after the start time of this event time.
     * If the given event time has an end time and the end time of this event time is not null, the end time of this
     * event time must be before the end time of the given event time.
     *
     * @param other the other event time to check.
     * @return true if the given event time is not supported, false otherwise.
     */
    public boolean notSupport(EventTimeModel other) {
        return !supports(other);
    }

    /**
     * Returns the start time of this event time.
     *
     * @return the start time of this event time.
     */
    public OffsetDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of this event time, if present.
     *
     * <p>
     * The end time of this event time may be null if it is not specified.
     * In this case, this method returns an empty optional.
     *
     * @return an optional containing the end time of this event time, if present.
     */
    public Optional<OffsetDateTime> getEndTime() {
        return Optional.ofNullable(endTime);
    }

}
