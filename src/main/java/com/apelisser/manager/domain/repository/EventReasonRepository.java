package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.EventReason;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventReasonRepository extends CustomJpaRepository <EventReason, Long> {

    @Override
    @Query("""
        from EventReason er
        join fetch er.eventType et
        join fetch et.company ec
        left join fetch ec.person
        """)
    List<EventReason> findAll();

}
