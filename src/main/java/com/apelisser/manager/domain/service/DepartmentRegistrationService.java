package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.entity.Department;

import java.util.List;

public interface DepartmentRegistrationService {

    Department save(Department department);

    void delete(Long departmentId);

    Department findById(Long departmentId);

    List<Department> findAll();

}
