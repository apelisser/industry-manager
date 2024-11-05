package com.apelisser.manager.domain.services.impl;

import com.apelisser.manager.domain.exceptions.EntityInUseException;
import com.apelisser.manager.domain.exceptions.EntityNotFoundException;
import com.apelisser.manager.domain.entities.Company;
import com.apelisser.manager.domain.entities.EventType;
import com.apelisser.manager.domain.repositories.EventTypeRepository;
import com.apelisser.manager.domain.services.CompanyRegistrationService;
import com.apelisser.manager.domain.services.EventTypeRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeRegistrationServiceImpl implements EventTypeRegistrationService {

    private final EventTypeRepository eventTypeRepository;
    private final CompanyRegistrationService companyService;

    public EventTypeRegistrationServiceImpl(EventTypeRepository eventTypeRepository,
            CompanyRegistrationService companyService) {
        this.eventTypeRepository = eventTypeRepository;
        this.companyService = companyService;
    }

    @Override
    public EventType save(EventType eventType) {
        Long companyId = eventType.getCompany().getId();
        Company company = companyService.findById(companyId);
        eventType.setCompany(company);
        return eventTypeRepository.save(eventType);
    }

    @Override
    public void delete(Long eventTypeId) {
        try {
            eventTypeRepository.deleteById(eventTypeId);
            eventTypeRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(EventType.class, eventTypeId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(EventType.class, eventTypeId, e);
        }
    }

    @Override
    public EventType findById(Long eventTypeId) {
        return eventTypeRepository.findById(eventTypeId)
            .orElseThrow(() -> new EntityNotFoundException(EventType.class, eventTypeId));
    }

    @Override
    public List<EventType> findAll() {
        return eventTypeRepository.findAll();
    }

}
