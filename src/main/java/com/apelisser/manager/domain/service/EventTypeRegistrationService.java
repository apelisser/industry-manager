package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.EventType;

import java.util.List;

public interface EventTypeRegistrationService {

    EventType save(EventType eventType);

    void delete(String eventTypeId);

    EventType findById(String eventTypeId);

    List<EventType> findAll();

}
