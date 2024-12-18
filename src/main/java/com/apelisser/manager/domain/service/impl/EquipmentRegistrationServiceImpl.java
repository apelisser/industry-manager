package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.model.Department;
import com.apelisser.manager.domain.model.Equipment;
import com.apelisser.manager.domain.repository.EquipmentRepository;
import com.apelisser.manager.domain.service.DepartmentRegistrationService;
import com.apelisser.manager.domain.service.EquipmentRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EquipmentRegistrationServiceImpl implements EquipmentRegistrationService {

    private final EquipmentRepository equipmentRepository;
    private final DepartmentRegistrationService departmentService;

    public EquipmentRegistrationServiceImpl(EquipmentRepository equipmentRepository,
            DepartmentRegistrationService departmentService) {
        this.equipmentRepository = equipmentRepository;
        this.departmentService = departmentService;
    }

    @Override
    public Equipment save(Equipment equipment) {
        Long departmentId = equipment.getDepartment().getId();
        Department department = departmentService.findById(departmentId);
        equipment.setDepartment(department);
        return equipmentRepository.save(equipment);
    }

    @Override
    public void delete(Long equipmentId) {
        try {
            equipmentRepository.deleteById(equipmentId);
            equipmentRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(Equipment.class, equipmentId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(Equipment.class, equipmentId, e);
        }
    }

    @Override
    public Equipment findById(Long equipmentId) {
        return equipmentRepository.findById(equipmentId)
            .orElseThrow(() -> new EntityNotFoundException(Equipment.class, equipmentId));
    }

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

}
