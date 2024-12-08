package com.apelisser.manager.domain.services.impl;

import com.apelisser.manager.domain.exceptions.EntityInUseException;
import com.apelisser.manager.domain.exceptions.EntityNotFoundException;
import com.apelisser.manager.domain.entities.City;
import com.apelisser.manager.domain.entities.State;
import com.apelisser.manager.domain.repositories.CityRepository;
import com.apelisser.manager.domain.services.CityRegistrationService;
import com.apelisser.manager.domain.services.StateRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityRegistrationServiceImpl implements CityRegistrationService {

    private final CityRepository cityRepository;
    private final StateRegistrationService stateService;

    public CityRegistrationServiceImpl(CityRepository cityRepository, StateRegistrationService stateService) {
        this.cityRepository = cityRepository;
        this.stateService = stateService;
    }

    @Override
    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateService.findById(stateId);
        city.setState(state);
        return cityRepository.save(city);
    }

    @Override
    public void delete(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
            cityRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(City.class, cityId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(City.class, cityId, e);
        }
    }

    @Override
    public City findById(Long cityId) {
        return cityRepository.findById(cityId)
            .orElseThrow(() -> new EntityNotFoundException(City.class, cityId));
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

}