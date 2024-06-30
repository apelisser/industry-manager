package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.State;

import java.util.List;

public interface StateRegistrationService {

    State save(State state);

    void delete(Long stateId);

    State findById(Long stateId);

    List<State> findAll();

}
