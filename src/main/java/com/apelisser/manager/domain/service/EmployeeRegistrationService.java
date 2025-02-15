package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Employee;

import java.util.List;

public interface EmployeeRegistrationService {

    Employee save(Employee employee);

    void delete(String employeeId);

    Employee findById(String employeeId);

    List<Employee> findAll();

}
