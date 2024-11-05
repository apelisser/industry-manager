package com.apelisser.manager.domain.services.impl;

import com.apelisser.manager.domain.exceptions.EntityInUseException;
import com.apelisser.manager.domain.exceptions.EntityNotFoundException;
import com.apelisser.manager.domain.entities.Country;
import com.apelisser.manager.domain.repositories.CountryRepository;
import com.apelisser.manager.domain.services.CountryRegistrationService;
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
    public void delete(Long countryId) {
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
    public Country findById(Long countryId) {
        return countryRepository.findById(countryId)
            .orElseThrow(() -> new EntityNotFoundException(Country.class, countryId));
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

}
