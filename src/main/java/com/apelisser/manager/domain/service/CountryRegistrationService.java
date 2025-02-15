package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Country;

import java.util.List;

public interface CountryRegistrationService {

    Country save(Country country);

    void delete(String countryId);

    Country findById(String countryId);

    List<Country> findAll();

}
