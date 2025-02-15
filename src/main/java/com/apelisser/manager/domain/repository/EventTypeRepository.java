package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.EventType;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventTypeRepository extends CustomJpaRepository <EventType, String> {

    @Override
    @Query("""
        from EventType et
        join fetch et.company c
        join fetch c.person
        """)
    List<EventType> findAll();

}
