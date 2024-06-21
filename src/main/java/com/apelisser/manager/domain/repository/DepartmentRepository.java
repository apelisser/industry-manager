package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.Department;
import com.apelisser.manager.domain.model.State;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CustomJpaRepository<Department, Long> {
}
