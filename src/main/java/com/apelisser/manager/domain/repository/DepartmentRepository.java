package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends CustomJpaRepository<Department, Long> {

    void deleteByIdAndCompanyId(Long departmentId, Long companyId);

    Optional<Department> findByIdAndCompanyId(Long departmentId, Long companyId);

    List<Department> findAllByCompanyId(Long companyId);

}
