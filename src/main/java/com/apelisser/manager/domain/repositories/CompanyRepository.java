package com.apelisser.manager.domain.repositories;

import com.apelisser.manager.domain.entities.Company;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends CustomJpaRepository<Company, Long> {

    @Override
    @Query("from Company c join fetch c.person")
    List<Company> findAll();
}
