package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.model.Company;
import com.apelisser.manager.domain.model.EventType;
import com.apelisser.manager.domain.repository.EventTypeRepository;
import com.apelisser.manager.domain.service.CompanyRegistrationService;
import com.apelisser.manager.domain.service.EventTypeRegistrationService;
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
