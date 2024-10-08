package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EventReasonNotFoundException;
import com.apelisser.manager.domain.model.EventReason;
import com.apelisser.manager.domain.model.EventType;
import com.apelisser.manager.domain.repository.EventReasonRepository;
import com.apelisser.manager.domain.service.EventReasonRegistrationService;
import com.apelisser.manager.domain.service.EventTypeRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventReasonRegistrationServiceImpl implements EventReasonRegistrationService {

    private final EventReasonRepository eventReasonRepository;
    private final EventTypeRegistrationService eventTypeService;

    public EventReasonRegistrationServiceImpl(EventReasonRepository eventReasonRepository, EventTypeRegistrationService eventTypeService) {
        this.eventReasonRepository = eventReasonRepository;
        this.eventTypeService = eventTypeService;
    }

    @Override
    public EventReason save(EventReason eventReason) {
        Long eventTypeId = eventReason.getEventType().getId();
        EventType eventType = eventTypeService.findById(eventTypeId);
        eventReason.setEventType(eventType);
        return eventReasonRepository.save(eventReason);
    }

    @Override
    public void delete(Long eventReasonId) {
        try {
            eventReasonRepository.deleteById(eventReasonId);
            eventReasonRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EventReasonNotFoundException(eventReasonId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(EventReason.class, eventReasonId, e);
        }
    }

    @Override
    public EventReason findById(Long eventReasonId) {
        return eventReasonRepository.findById(eventReasonId)
            .orElseThrow(() -> new EventReasonNotFoundException(eventReasonId));
    }

    @Override
    public List<EventReason> findAll() {
        return eventReasonRepository.findAll();
    }

}
