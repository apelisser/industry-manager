package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.model.Company;
import com.apelisser.manager.domain.model.Position;
import com.apelisser.manager.domain.repository.PositionRepository;
import com.apelisser.manager.domain.service.CompanyRegistrationService;
import com.apelisser.manager.domain.service.PositionRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionRegistrationServiceImpl implements PositionRegistrationService {

    private final PositionRepository positionRepository;
    private final CompanyRegistrationService companyService;

    public PositionRegistrationServiceImpl(PositionRepository positionRepository,
        CompanyRegistrationService companyService) {
        this.positionRepository = positionRepository;
        this.companyService = companyService;
    }

    @Override
    public Position save(Position position) {
        Long companyId = position.getCompany().getId();
        Company company = companyService.findById(companyId);
        position.setCompany(company);

        if (hasSuperior(position)) {
            Long superiorId = position.getSuperior().getId();
            Position positionFound = findById(superiorId);
            position.setSuperior(positionFound);
        }

        return positionRepository.save(position);
    }

    @Override
    public void delete(Long positionId) {
        try {
            positionRepository.deleteById(positionId);
            positionRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(Position.class, positionId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(Position.class, positionId, e);
        }
    }

    @Override
    public Position findById(Long positionId) {
        return positionRepository.findById(positionId)
            .orElseThrow(() -> new EntityNotFoundException(Position.class, positionId));
    }

    @Override
    public List<Position> findAll() {
        return positionRepository.findAll();
    }


    private boolean hasSuperior(Position position) {
        Position superior = position.getSuperior();
        return superior != null && superior.getId() != null;
    }

}
