package com.apelisser.manager.application.api.exceptionhandler;

import com.apelisser.manager.application.api.exceptionhandler.model.Problem;
import com.apelisser.manager.application.api.exceptionhandler.model.ProblemType;
import com.apelisser.manager.core.context.Context;
import com.apelisser.manager.core.i18n.MessageManager;
import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.exception.EquipmentDowntimeOverlapException;
import com.apelisser.manager.domain.exception.EventOutOfRangeException;
import com.apelisser.manager.domain.exception.EventTimeOverlapException;
import com.apelisser.manager.domain.exception.ParentEventUpdateNotAllowedException;
import com.apelisser.manager.domain.exception.PersonInvalidException;
import com.apelisser.manager.domain.model.EquipmentDowntime;
import com.apelisser.manager.domain.model.EventTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.apelisser.manager.core.i18n.MessageConstants.*;

@RestControllerAdvice
public class ApiExceptionHandler extends ExceptionHandlingHelper {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    public ApiExceptionHandler(MessageManager messageManager, Context context) {
        super(log, context, messageManager);
    }

    @ExceptionHandler(EntityInUseException.class)
    private ProblemDetail handleEntityInUseException(EntityInUseException e) {
        HttpStatusCode status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.POLICY_VIOLATION;
        String userMessage = getMessage(EX_ENTITY_IN_USE_MESSAGE, e.getEntityClassName());
        String detailMessage = getMessage(EX_ENTITY_IN_USE_DETAIL, e.getEntityClassName(), e.getIdValue());

        Problem problem = createProblemBuilder(status, problemType, detailMessage)
            .userMessage(userMessage)
            .build();

        return problem.toProblemDetail();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ProblemDetail handleEntityNotFoundException(EntityNotFoundException e) {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.INVALID_DATA;
        String userMessage = getMessage(EX_ENTITY_NOT_FOUND_MESSAGE, e.getEntityClassName());
        String detailMessage = getMessage(EX_ENTITY_NOT_FOUND_DETAIL, e.getEntityClassName(), e.getIdentifier());

        Problem problem = createProblemBuilder(status, problemType, detailMessage)
            .userMessage(userMessage)
            .build();

        return problem.toProblemDetail();
    }

    @ExceptionHandler(EquipmentDowntimeOverlapException.class)
    private ProblemDetail handleEquipmentDowntimeOverlapException(EquipmentDowntimeOverlapException e) {
        HttpStatusCode status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.POLICY_VIOLATION;

        EquipmentDowntime equipmentDowntime = e.getEquipmentDowntime();
        EquipmentDowntime overlappingEquipmentDowntime = e.getOverlappingEquipmentDowntime();

        String userMessage = getMessage(EX_EQUIPMENT_DOWNTIME_OVERLAP_MESSAGE);
        String detailMessage = getMessage(EX_EQUIPMENT_DOWNTIME_OVERLAP_DETAIL,
            equipmentDowntime.getEvent().getId(),
            equipmentDowntime.getStartTime(),
            equipmentDowntime.getEndTime(),
            overlappingEquipmentDowntime.getEvent().getId(),
            overlappingEquipmentDowntime.getStartTime(),
            overlappingEquipmentDowntime.getEndTime());

        Problem problem = createProblemBuilder(status, problemType, detailMessage)
            .userMessage(userMessage)
            .build();

        return problem.toProblemDetail();
    }

    @ExceptionHandler(EventOutOfRangeException.class)
    private ProblemDetail handleEventOutOfRangeException(EventOutOfRangeException e) {
        HttpStatusCode status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.POLICY_VIOLATION;

        EquipmentDowntime parentEvent = e.getParentEvent();
        EventTime eventExceeding = e.getEventExceeding();

        String userMessage = getMessage(EX_EVENT_OUT_OF_RANGE_MESSAGE);
        String detailMessage = getMessage(EX_EVENT_OUT_OF_RANGE_DETAIL,
            eventExceeding.getEvent().getId(),
            eventExceeding.getType(),
            eventExceeding.getStartTime(),
            eventExceeding.getEndTime(),
            parentEvent.getStartTime(),
            parentEvent.getEndTime());

        Problem problem = createProblemBuilder(status, problemType, detailMessage)
            .userMessage(userMessage)
            .build();

        return problem.toProblemDetail();
    }

    @ExceptionHandler(EventTimeOverlapException.class)
    private ProblemDetail handleEventTimeOverlapException(EventTimeOverlapException e) {
        HttpStatusCode status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.POLICY_VIOLATION;

        EventTime eventTime = e.getEventTime();
        EventTime overlappingEventTime = e.getOverlappingEventTime();

        String userMessage = getMessage(EX_EVENT_TIME_OVERLAP_MESSAGE);
        String detailMessage = getMessage(EX_EVENT_TIME_OVERLAP_DETAIL,
            eventTime.getEvent().getId(),
            eventTime.getType(),
            eventTime.getStartTime(),
            eventTime.getEndTime(),
            overlappingEventTime.getEvent().getId(),
            overlappingEventTime.getType(),
            overlappingEventTime.getStartTime(),
            overlappingEventTime.getEndTime());

        Problem problem = createProblemBuilder(status, problemType, detailMessage)
            .userMessage(userMessage)
            .build();

        return problem.toProblemDetail();
    }

    @ExceptionHandler(ParentEventUpdateNotAllowedException.class)
    private ProblemDetail handleParentEventUpdateException(ParentEventUpdateNotAllowedException e) {
        HttpStatusCode status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.POLICY_VIOLATION;
        String userMessage = getMessage(EX_PARENT_EVENT_UPDATE_NOT_ALLOWED_MESSAGE);
        String detailMessage = getMessage(EX_PARENT_EVENT_UPDATE_NOT_ALLOWED_DETAIL, e.getEventName());

        Problem problem = createProblemBuilder(status, problemType, detailMessage)
            .userMessage(userMessage)
            .build();

        return problem.toProblemDetail();
    }

    @ExceptionHandler(PersonInvalidException.class)
    private ProblemDetail handlePersonInvalidException(PersonInvalidException e) {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.INVALID_DATA;
        String userMessage = getMessage(EX_PERSON_INVALID_MESSAGE, e.getType());
        String detailMessage = getMessage(EX_PERSON_INVALID_DETAIL, e.getValue(), e.getType());

        Problem problem = createProblemBuilder(status, problemType, detailMessage)
            .userMessage(userMessage)
            .build();

        return problem.toProblemDetail();
    }

    /**
     * Handles an uncaught exception. This method is called when an exception is not
     * handled by any other exception handler. It is used to generate a
     * {@link ProblemDetail} object that contains information about the exception.
     *
     * @param e the exception to handle
     * @return a {@link ProblemDetail} object containing information about the
     *         exception
     */
    @ExceptionHandler(Exception.class)
    private ProblemDetail handleUncaughtException(Exception e) {
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String genericMessage = getMessage(GENERIC_USER_MESSAGE);

        log.error(e.getMessage(), e);

        Problem problem = createProblemBuilder(status, problemType, genericMessage)
            .userMessage(genericMessage)
            .build();

        return problem.toProblemDetail();
    }

}
