package com.apelisser.manager.domain.repositories;

import com.apelisser.manager.domain.entities.EventType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventTypeRepository extends CustomJpaRepository <EventType, Long> {

    @Override
    @Query("""
        from EventType et
        join fetch et.company c
        join fetch c.person
        """)
    List<EventType> findAll();

}
