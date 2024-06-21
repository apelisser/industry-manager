package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.City;
import com.apelisser.manager.domain.model.Position;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CustomJpaRepository<Position, Long> {
}
