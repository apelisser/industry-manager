package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.Country;

import java.util.List;

public interface CountryRegistrationService {

    Country save(Country country);

    void delete(Long countryId);

    Country findById(Long countryId);

    List<Country> findAll();

}
