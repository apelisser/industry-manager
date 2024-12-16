package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.entity.Department;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends CustomJpaRepository<Department, Long> {

    @Override
    @Query("from Department d join fetch d.company c join fetch c.person")
    List<Department> findAll();
}
