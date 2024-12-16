package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.entity.Position;

import java.util.List;

public interface PositionRegistrationService {

    Position save(Position position);

    void delete(Long positionId);

    Position findById(Long positionId);

    List<Position> findAll();

}
