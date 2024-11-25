package com.apelisser.manager.domain.services.impl;

import com.apelisser.manager.domain.entities.Employee;
import com.apelisser.manager.domain.entities.Equipment;
import com.apelisser.manager.domain.entities.EquipmentDowntime;
import com.apelisser.manager.domain.entities.Event;
import com.apelisser.manager.domain.exceptions.EntityInUseException;
import com.apelisser.manager.domain.exceptions.EntityNotFoundException;
import com.apelisser.manager.domain.repositories.EquipmentDowntimeRepository;
import com.apelisser.manager.domain.services.DatabaseDowntimeValidationService;
import com.apelisser.manager.domain.services.EmployeeRegistrationService;
import com.apelisser.manager.domain.services.EquipmentDowntimeRegistrationService;
import com.apelisser.manager.domain.services.EquipmentRegistrationService;
import com.apelisser.manager.domain.services.EventRegistrationService;
import com.apelisser.manager.domain.services.LocalDowntimeValidationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentDowntimeRegistrationServiceImpl implements EquipmentDowntimeRegistrationService {

    private final EventRegistrationService eventService;
    private final EmployeeRegistrationService employeeService;
    private final EquipmentRegistrationService equipmentService;
    private final EquipmentDowntimeRepository downtimeRepository;
    private final DatabaseDowntimeValidationService databaseValidationService;
    private final LocalDowntimeValidationService localValidationService;

    public EquipmentDowntimeRegistrationServiceImpl(EventRegistrationService eventService,
            EmployeeRegistrationService employeeService, EquipmentRegistrationService equipmentService,
            EquipmentDowntimeRepository downtimeRepository, DatabaseDowntimeValidationService databaseValidationService,
            LocalDowntimeValidationService localValidationService) {
        this.eventService = eventService;
        this.employeeService = employeeService;
        this.equipmentService = equipmentService;
        this.downtimeRepository = downtimeRepository;
        this.databaseValidationService = databaseValidationService;
        this.localValidationService = localValidationService;
    }

    @Override
    public EquipmentDowntime save(EquipmentDowntime equipmentDowntime) {
        localValidationService.validate(equipmentDowntime);
        databaseValidationService.validate(equipmentDowntime);
        loadRelationships(equipmentDowntime);
        return downtimeRepository.save(equipmentDowntime);
    }

    @Override
    public void delete(Long equipmentDowntimeId) {
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
    public EquipmentDowntime findById(Long equipmentDowntimeId) {
        return downtimeRepository.findById(equipmentDowntimeId)
            .orElseThrow(() -> new EntityNotFoundException(EquipmentDowntime.class, equipmentDowntimeId));
    }

    @Override
    public List<EquipmentDowntime> findAll() {
        return downtimeRepository.findAll();
    }

    public void loadRelationships(EquipmentDowntime equipmentDowntime) {
        Equipment equipment = equipmentService.findById(equipmentDowntime.getEquipment().getId());
        equipmentDowntime.setEquipment(equipment);

        Event mainEvent = eventService.findById(equipmentDowntime.getEvent().getId());
        equipmentDowntime.setEvent(mainEvent);

        Employee employee = employeeService.findById(equipmentDowntime.getEmployee().getId());
        equipmentDowntime.setEmployee(employee);

        equipmentDowntime.getRelatedEvents().forEach(eventTime -> {
            Event relatedEvent = eventService.findById(eventTime.getEvent().getId());
            eventTime.setEvent(relatedEvent);
        });
    }

}
