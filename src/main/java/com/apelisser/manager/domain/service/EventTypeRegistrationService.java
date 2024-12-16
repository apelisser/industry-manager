package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.entity.EventType;

import java.util.List;

public interface EventTypeRegistrationService {

    EventType save(EventType eventType);

    void delete(Long eventTypeId);

    EventType findById(Long eventTypeId);

    List<EventType> findAll();

}
