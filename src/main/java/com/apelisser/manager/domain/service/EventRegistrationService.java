package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Event;

import java.util.List;

public interface EventRegistrationService {

    Event save(Event event);

    void delete(Long eventId);

    Event findById(Long eventId);

    List<Event> findAllRoots();

    List<Event> findAllChildren(Long parentEventId);

    List<Event> findAll();

    void inactivate(Long eventId);

    void activate(Long eventId);
}
