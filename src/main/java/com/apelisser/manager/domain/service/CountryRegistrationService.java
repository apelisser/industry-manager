package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Country;

public interface CountryRegistrationService {

    Country save(Country country);

    void delete(Long countryId);

    Country search(Long countryId);

}
