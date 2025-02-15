package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Department;

import java.util.List;

public interface DepartmentRegistrationService {

    Department save(Department department);

    void delete(String departmentId);

    Department findById(String departmentId);

    List<Department> findAll();

}
