package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Department;

import java.util.List;

public interface DepartmentRegistrationService {

    Department save(Department department);

    void delete(Long companyId, Long departmentId);

    Department findById(Long companyId, Long departmentId);

    List<Department> findAll(Long companyId);

}
