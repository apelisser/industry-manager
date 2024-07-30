package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Employee;

import java.util.List;

public interface EmployeeRegistrationService {

    Employee save(Employee employee);

    void delete(Long employeeId);

    Employee findById(Long employeeId);

    List<Employee> findAll();

}
