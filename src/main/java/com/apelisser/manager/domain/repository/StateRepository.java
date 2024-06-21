package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.State;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CustomJpaRepository<State, Long> {
}
