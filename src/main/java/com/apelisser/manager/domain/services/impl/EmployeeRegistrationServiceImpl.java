package com.apelisser.manager.domain.services.impl;

import com.apelisser.manager.domain.exceptions.EntityInUseException;
import com.apelisser.manager.domain.exceptions.EntityNotFoundException;
import com.apelisser.manager.domain.entities.Employee;
import com.apelisser.manager.domain.entities.Position;
import com.apelisser.manager.domain.repositories.EmployeeRepository;
import com.apelisser.manager.domain.services.EmployeeRegistrationService;
import com.apelisser.manager.domain.services.PositionRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeRegistrationServiceImpl implements EmployeeRegistrationService {

    private final EmployeeRepository employeeRepository;
    private final PositionRegistrationService positionService;

    public EmployeeRegistrationServiceImpl(EmployeeRepository employeeRepository,
        PositionRegistrationService positionService) {
        this.employeeRepository = employeeRepository;
        this.positionService = positionService;
    }

    @Override
    public Employee save(Employee employee) {
        Long positionId = employee.getPosition().getId();
        Position position = positionService.findById(positionId);
        employee.setPosition(position);
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
            employeeRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(Employee.class, employeeId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(Employee.class, employeeId, e);
        }
    }

    @Override
    public Employee findById(Long employeeId) {
        return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException(Employee.class, employeeId));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

}
