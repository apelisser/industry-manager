package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Position;

import java.util.List;

public interface PositionRegistrationService {

    Position save(Position position);

    void delete(String positionId);

    Position findById(String positionId);

    List<Position> findAll();

}
