package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.City;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CustomJpaRepository<City, Long> {
}
