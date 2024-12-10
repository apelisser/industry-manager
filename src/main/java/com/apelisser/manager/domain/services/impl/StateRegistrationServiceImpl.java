package com.apelisser.manager.domain.services.impl;

import com.apelisser.manager.domain.exceptions.EntityInUseException;
import com.apelisser.manager.domain.exceptions.EntityNotFoundException;
import com.apelisser.manager.domain.entities.Country;
import com.apelisser.manager.domain.entities.State;
import com.apelisser.manager.domain.repositories.StateRepository;
import com.apelisser.manager.domain.services.CountryRegistrationService;
import com.apelisser.manager.domain.services.StateRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateRegistrationServiceImpl implements StateRegistrationService {

    private final StateRepository stateRepository;
    private final CountryRegistrationService countryService;

    public StateRegistrationServiceImpl(StateRepository stateRepository, CountryRegistrationService countryService) {
        this.stateRepository = stateRepository;
        this.countryService = countryService;
    }

    @Override
    public State save(State state) {
        Long countryId = state.getCountry().getId();
        Country country = countryService.findById(countryId);
        state.setCountry(country);
        return stateRepository.save(state);
    }

    @Override
    public void delete(Long stateId) {
        try {
            stateRepository.deleteById(stateId);
            stateRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(State.class, stateId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(State.class, stateId, e);
        }
    }

    @Override
    public State findById(Long stateId) {
        return stateRepository.findById(stateId)
            .orElseThrow(() -> new EntityNotFoundException(State.class, stateId));
    }

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }

}
