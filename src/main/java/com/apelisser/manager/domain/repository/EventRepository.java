package com.apelisser.manager.domain.repository;

import com.apelisser.manager.domain.model.Event;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends CustomJpaRepository <Event, String> {

    @Query("""
        from Event e
        join fetch e.company c
        join fetch c.person
        where e.parent is null and e.status = 'ACTIVE'
        """)
    List<Event> findAllRoots();

    @Query("""
        from Event e
        left join fetch e.parent p
        join fetch e.company c
        join fetch c.person pe
        where p is not null
            and p.status = 'ACTIVE'
            and e.status = 'ACTIVE'
            and e.parent.id = :parentEventId
        """)
    List<Event> findAllChildren(String parentEventId);

}
