package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.State;

import java.util.List;

public interface StateRegistrationService {

    State save(State state);

    void delete(String stateId);

    State findById(String stateId);

    List<State> findAll();

}
