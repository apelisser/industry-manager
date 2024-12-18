package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.Company;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends CustomJpaRepository<Company, Long> {

    @Override
    @Query("from Company c join fetch c.person")
    List<Company> findAll();
}
