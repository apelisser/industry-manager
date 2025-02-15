package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.model.Country;
import com.apelisser.manager.domain.model.State;
import com.apelisser.manager.domain.repository.StateRepository;
import com.apelisser.manager.domain.service.CountryRegistrationService;
import com.apelisser.manager.domain.service.StateRegistrationService;
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
        String countryId = state.getCountry().getId();
        Country country = countryService.findById(countryId);
        state.setCountry(country);
        return stateRepository.save(state);
    }

    @Override
    public void delete(String stateId) {
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
    public State findById(String stateId) {
        return stateRepository.findById(stateId)
            .orElseThrow(() -> new EntityNotFoundException(State.class, stateId));
    }

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }

}
