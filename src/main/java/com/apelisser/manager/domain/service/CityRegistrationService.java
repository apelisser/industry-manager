package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.City;

import java.util.List;

public interface CityRegistrationService {

    City save(City city);

    void delete(Long cityId);

    City findById(Long cityId);

    List<City> findAll();

}
