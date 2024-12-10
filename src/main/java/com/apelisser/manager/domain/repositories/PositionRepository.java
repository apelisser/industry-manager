package com.apelisser.manager.domain.repositories;

import com.apelisser.manager.domain.entities.Position;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends CustomJpaRepository<Position, Long> {

    @Query("""
        from Position p
        join fetch p.company c
        join fetch c.person pe
        left join fetch p.superior s
        """)
    List<Position> findAll();

}
