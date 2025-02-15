package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.model.Country;
import com.apelisser.manager.domain.repository.CountryRepository;
import com.apelisser.manager.domain.service.CountryRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryRegistrationServiceImpl implements CountryRegistrationService {

    private final CountryRepository countryRepository;

    public CountryRegistrationServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void delete(String countryId) {
        try {
            countryRepository.deleteById(countryId);
            countryRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(Country.class, countryId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(Country.class, countryId, e);
        }
    }

    @Override
    public Country findById(String countryId) {
        return countryRepository.findById(countryId)
            .orElseThrow(() -> new EntityNotFoundException(Country.class, countryId));
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

}
