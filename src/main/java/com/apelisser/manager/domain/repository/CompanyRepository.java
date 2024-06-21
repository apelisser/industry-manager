package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.Equipment;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CustomJpaRepository<Equipment, Long> {
}
