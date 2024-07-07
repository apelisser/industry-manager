package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.mapper.CityInputDisassembler;
import com.apelisser.manager.api.v1.mapper.CityModelAssembler;
import com.apelisser.manager.api.v1.model.CityModel;
import com.apelisser.manager.api.v1.model.input.CityInput;
import com.apelisser.manager.domain.model.City;
import com.apelisser.manager.domain.service.CityRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
    path = "/api/v1/cities",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

    private final CityInputDisassembler cityDisassembler;
    private final CityModelAssembler cityAssembler;
    private final CityRegistrationService cityService;

    public CityController(CityInputDisassembler cityDisassembler, CityModelAssembler cityAssembler,
        CityRegistrationService cityService) {
        this.cityDisassembler = cityDisassembler;
        this.cityAssembler = cityAssembler;
        this.cityService = cityService;
    }

    @GetMapping
    public List<CityModel> findAll() {
        List<City> cities = cityService.findAll();
        return cityAssembler.toCollectionModel(cities);
    }

    @GetMapping("/{cityId}")
    public CityModel findById(@PathVariable Long cityId) {
        City city = cityService.findById(cityId);
        return cityAssembler.toModel(city);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CityModel add(@RequestBody CityInput cityInput) {
        City domainCity = cityDisassembler.toDomainObject(cityInput);
        City savedCity = cityService.save(domainCity);
        return cityAssembler.toModel(savedCity);
    }

    @PutMapping("/{cityId}")
    public CityModel update(@PathVariable Long cityId, @RequestBody CityInput cityInput) {
        City city = cityService.findById(cityId);
        cityDisassembler.copyToDomainObject(cityInput, city);
        City updatedCity = cityService.save(city);
        return cityAssembler.toModel(updatedCity);
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cityId) {
        cityService.delete(cityId);
    }

}
