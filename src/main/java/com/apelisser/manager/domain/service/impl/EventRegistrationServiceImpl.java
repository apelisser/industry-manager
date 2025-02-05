package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.enums.RecordStatus;
import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.exception.ParentEventUpdateNotAllowedException;
import com.apelisser.manager.domain.model.Company;
import com.apelisser.manager.domain.model.Event;
import com.apelisser.manager.domain.repository.EventRepository;
import com.apelisser.manager.domain.service.CompanyRegistrationService;
import com.apelisser.manager.domain.service.EventRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EventRegistrationServiceImpl implements EventRegistrationService {

    private final EventRepository eventRepository;
    private final CompanyRegistrationService companyService;

    public EventRegistrationServiceImpl(EventRepository eventRepository, CompanyRegistrationService companyService) {
        this.eventRepository = eventRepository;
        this.companyService = companyService;
    }

    @Override
    public Event save(Event event) {
        if (isUpdatingParent(event)) {
            throw new ParentEventUpdateNotAllowedException(event.getName());
        }

        Long companyId = event.getCompany().getId();
        Company company = companyService.findById(companyId);
        event.setCompany(company);
        return eventRepository.save(event);
    }

    @Override
    public void delete(Long eventId) {
        try {
            eventRepository.deleteById(eventId);
            eventRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(Event.class, eventId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(Event.class, eventId, e);
        }
    }

    @Override
    public Event findById(Long eventId) {
        return eventRepository.findById(eventId)
            .orElseThrow(() -> new EntityNotFoundException(Event.class, eventId));
    }

    @Override
    public List<Event> findAllRoots() {
        return eventRepository.findAllRoots();
    }

    @Override
    public List<Event> findAllChildren(Long parentEventId) {
        Event parent = findById(parentEventId);
        return eventRepository.findAllChildren(parent.getId());
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public void inactivate(Long eventId) {
        Event event = findById(eventId);
        if (!event.isInactive()) {
            event.setStatus(RecordStatus.INACTIVE);
            eventRepository.save(event);
        }
    }

    @Override
    public void activate(Long eventId) {
        Event event = findById(eventId);
        if (!event.isActive()) {
            event.setStatus(RecordStatus.ACTIVE);
            eventRepository.save(event);
        }
    }

    private boolean isUpdatingParent(Event event) {
        boolean isUpdating = event.getId() != null;

        if (!isUpdating) {
            return false;
        }

        eventRepository.detach(event);

        /*
        1: original.parent == null && event.parent == null -> return false
        2: original.parent == null && event.parent != null -> return true
        3: original.parent != null && event.parent == null -> return true
        4: original.parentId != event.parentId             -> return true, otherwise return false
         */

        Event original = findById(event.getId());

        // 1
        if (original.isRoot() && event.isRoot()) {
            return false;
        }

        // 2, 3
        if (original.isRoot() || event.isRoot()) {
            return true;
        }

        // 4
        Long originalParentId = original.getParent().getId();
        Long eventParentId = event.getParent().getId();
        return !Objects.equals(originalParentId, eventParentId);
    }

}
