package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.State;

import java.util.List;

public interface StateRegistrationService {

    State save(State state);

    void delete(Long stateId);

    State findById(Long stateId);

    List<State> findAll();

}
