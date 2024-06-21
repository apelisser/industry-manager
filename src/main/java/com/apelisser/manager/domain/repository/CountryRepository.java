package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CustomJpaRepository<Country, Long> {
}
