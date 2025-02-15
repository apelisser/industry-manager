package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.model.Employee;
import com.apelisser.manager.domain.model.Equipment;
import com.apelisser.manager.domain.model.EquipmentDowntime;
import com.apelisser.manager.domain.model.Event;
import com.apelisser.manager.domain.model.EventTime;
import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.repository.EquipmentDowntimeRepository;
import com.apelisser.manager.domain.service.DatabaseDowntimeValidationService;
import com.apelisser.manager.domain.service.EmployeeRegistrationService;
import com.apelisser.manager.domain.service.EquipmentDowntimeRegistrationService;
import com.apelisser.manager.domain.service.EquipmentRegistrationService;
import com.apelisser.manager.domain.service.EventRegistrationService;
import com.apelisser.manager.domain.service.LocalDowntimeValidationService;
import com.apelisser.manager.domain.util.Assert;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentDowntimeRegistrationServiceImpl implements EquipmentDowntimeRegistrationService {

    private final EventRegistrationService eventService;
    private final EmployeeRegistrationService employeeService;
    private final EquipmentRegistrationService equipmentService;
    private final EquipmentDowntimeRepository downtimeRepository;
    private final DatabaseDowntimeValidationService databaseValidationService;
    private final LocalDowntimeValidationService localValidationService;
    private final EventRegistrationService eventRegistrationService;

    public EquipmentDowntimeRegistrationServiceImpl(EventRegistrationService eventService,
            EmployeeRegistrationService employeeService, EquipmentRegistrationService equipmentService,
            EquipmentDowntimeRepository downtimeRepository, DatabaseDowntimeValidationService databaseValidationService,
            LocalDowntimeValidationService localValidationService, EventRegistrationService eventRegistrationService) {
        this.eventService = eventService;
        this.employeeService = employeeService;
        this.equipmentService = equipmentService;
        this.downtimeRepository = downtimeRepository;
        this.databaseValidationService = databaseValidationService;
        this.localValidationService = localValidationService;
        this.eventRegistrationService = eventRegistrationService;
    }

    @Override
    public EquipmentDowntime save(EquipmentDowntime equipmentDowntime) {
        localValidationService.validate(equipmentDowntime);
        databaseValidationService.validate(equipmentDowntime);
        loadRelationships(equipmentDowntime);
        return downtimeRepository.save(equipmentDowntime);
    }

    @Override
    public void delete(String equipmentDowntimeId) {
        try {
            downtimeRepository.deleteById(equipmentDowntimeId);
            downtimeRepository.flush();
        } catch (EmptyResultDataAccessException e ) {
            throw new EntityNotFoundException(EquipmentDowntime.class, equipmentDowntimeId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(EquipmentDowntime.class, equipmentDowntimeId, e);
        }
    }

    @Override
    public void deleteRelatedEventTime(String equipmentDowntimeId, String eventTimeId) {
        Assert.notNull(equipmentDowntimeId, "The equipment downtime id must not be null.");
        Assert.notNull(eventTimeId, "The event time id must not be null.");

        EquipmentDowntime equipmentDowntime = findById(equipmentDowntimeId);

        EventTime eventTime = equipmentDowntime.getRelatedEvents().stream()
            .filter(currentEventTime -> currentEventTime.getId().equals(eventTimeId))
            .findFirst()
            .orElseThrow(() -> {
                String message = String.format(
                    "The event time with id %s is not related to the equipment downtime with id %s.",
                    eventTimeId,
                    equipmentDowntimeId
                );
                return new EntityNotFoundException(EventTime.class, eventTimeId, message);
            });

        equipmentDowntime.getRelatedEvents().remove(eventTime);
        downtimeRepository.save(equipmentDowntime);
    }

    @Override
    public EquipmentDowntime addRelatedEventsTime(String equipmentDowntimeId, List<EventTime> eventsTime) {
        // Validate the list of EventTime objects against each other
        localValidationService.validateEventsTime(eventsTime);

        // Set the event property of each EventTime to the corresponding event
        eventsTime.forEach(eventTime -> {
            String eventId = eventTime.getEvent().getId();
            Event event = eventRegistrationService.findById(eventId);
            eventTime.setEvent(event);
        });

        // Retrieve the EquipmentDowntime entity by its ID
        EquipmentDowntime downtime = findById(equipmentDowntimeId);

        // Validate each EventTime against the equipment downtime
        eventsTime.forEach(eventTime ->
            localValidationService.validateEventTimeForDowntime(eventTime, downtime));

        downtime.getRelatedEvents().addAll(eventsTime);
        return downtimeRepository.save(downtime);
    }

    @Override
    public EquipmentDowntime findById(String equipmentDowntimeId) {
        return downtimeRepository.findById(equipmentDowntimeId)
            .orElseThrow(() -> new EntityNotFoundException(EquipmentDowntime.class, equipmentDowntimeId));
    }

    @Override
    public Optional<EquipmentDowntime> findFirstOverlap(EquipmentDowntime equipmentDowntime) {
        return downtimeRepository.findFirstOverlap(equipmentDowntime);
    }

    @Override
    public List<EquipmentDowntime> findAllOverlaps(EquipmentDowntime equipmentDowntime) {
        return downtimeRepository.findAllOverlaps(equipmentDowntime);
    }

    @Override
    public List<EquipmentDowntime> findAll() {
        return downtimeRepository.findAll();
    }

    private void loadRelationships(EquipmentDowntime equipmentDowntime) {
        Equipment equipment = equipmentService.findById(equipmentDowntime.getEquipment().getId());
        equipmentDowntime.setEquipment(equipment);

        Event mainEvent = eventService.findById(equipmentDowntime.getEvent().getId());
        equipmentDowntime.setEvent(mainEvent);

        Employee employee = employeeService.findById(equipmentDowntime.getEmployee().getId());
        equipmentDowntime.setEmployee(employee);

        equipmentDowntime.getRelatedEvents().forEach(eventTime -> {
            Event event = eventService.findById(eventTime.getEvent().getId());
            eventTime.setEvent(event);
        });
    }

}
