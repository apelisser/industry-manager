package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.entity.Equipment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EquipmentRepository extends CustomJpaRepository<Equipment, Long> {

    @Override
    @Query("""
        from Equipment e
        join fetch e.department ed
        left join fetch ed.company ec
        left join fetch ec.person ep
        join fetch e.pieces
        """)
    List<Equipment> findAll();

    @Override
    @Query("""
        from Equipment e
        join fetch e.department ed
        left join fetch ed.company ec
        left join fetch ec.person ep
        join fetch e.pieces
        where e.id = :id
        """)
    Optional<Equipment> findById(Long id);
}
