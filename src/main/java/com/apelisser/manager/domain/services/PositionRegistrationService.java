package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.Position;

import java.util.List;

public interface PositionRegistrationService {

    Position save(Position position);

    void delete(Long positionId);

    Position findById(Long positionId);

    List<Position> findAll();

}
