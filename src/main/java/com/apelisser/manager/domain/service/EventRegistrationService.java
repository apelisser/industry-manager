package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Event;

import java.util.List;

public interface EventRegistrationService {

    Event save(Event event);

    void delete(String eventId);

    Event findById(String eventId);

    List<Event> findAllRoots();

    List<Event> findAllChildren(String parentEventId);

    List<Event> findAll();

    void inactivate(String eventId);

    void activate(String eventId);
}
