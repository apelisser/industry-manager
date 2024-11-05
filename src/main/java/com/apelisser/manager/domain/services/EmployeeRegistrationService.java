package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.Employee;

import java.util.List;

public interface EmployeeRegistrationService {

    Employee save(Employee employee);

    void delete(Long employeeId);

    Employee findById(Long employeeId);

    List<Employee> findAll();

}
