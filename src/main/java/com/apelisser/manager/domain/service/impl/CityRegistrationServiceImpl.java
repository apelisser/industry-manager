package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.exception.CityNotFoundException;
import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.model.City;
import com.apelisser.manager.domain.model.State;
import com.apelisser.manager.domain.repository.CityRepository;
import com.apelisser.manager.domain.service.CityRegistrationService;
import com.apelisser.manager.domain.service.StateRegistrationService;
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
            throw new CityNotFoundException(cityId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(City.class, cityId, e);
        }
    }

    @Override
    public City findById(Long cityId) {
        return cityRepository.findById(cityId)
            .orElseThrow(() -> new CityNotFoundException(cityId));
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

}
