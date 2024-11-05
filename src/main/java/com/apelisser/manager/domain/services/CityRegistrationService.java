package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.City;

import java.util.List;

public interface CityRegistrationService {

    City save(City city);

    void delete(Long cityId);

    City findById(Long cityId);

    List<City> findAll();

}
