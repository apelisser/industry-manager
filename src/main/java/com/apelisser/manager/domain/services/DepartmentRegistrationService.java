package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.Department;

import java.util.List;

public interface DepartmentRegistrationService {

    Department save(Department department);

    void delete(Long departmentId);

    Department findById(Long departmentId);

    List<Department> findAll();

}
