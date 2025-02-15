package com.apelisser.manager.application.api.v1.controller;

import com.apelisser.manager.application.api.v1.mapper.CountryInputDisassembler;
import com.apelisser.manager.application.api.v1.mapper.CountryModelAssembler;
import com.apelisser.manager.application.api.v1.model.CountryModel;
import com.apelisser.manager.application.api.v1.model.input.CountryInput;
import com.apelisser.manager.domain.model.Country;
import com.apelisser.manager.domain.service.CountryRegistrationService;
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
    path = "/api/v1/countries",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryInputDisassembler countryDisassembler;
    private final CountryModelAssembler countryAssembler;
    private final CountryRegistrationService countryService;

    public CountryController(CountryInputDisassembler countryDisassembler, CountryModelAssembler countryAssembler,
        CountryRegistrationService countryService) {
        this.countryDisassembler = countryDisassembler;
        this.countryAssembler = countryAssembler;
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryModel> findAll() {
        List<Country> countries = countryService.findAll();
        return countryAssembler.toCollectionModel(countries);
    }

    @GetMapping("/{countryId}")
    public CountryModel findById(@PathVariable String countryId) {
        Country country = countryService.findById(countryId);
        return countryAssembler.toModel(country);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CountryModel add(@RequestBody CountryInput countryInput) {
        Country domainCountry = countryDisassembler.toDomainObject(countryInput);
        Country savedCountry = countryService.save(domainCountry);
        return countryAssembler.toModel(savedCountry);
    }

    @PutMapping("/{countryId}")
    public CountryModel update(@PathVariable String countryId, @RequestBody CountryInput countryInput) {
        Country country = countryService.findById(countryId);
        countryDisassembler.copyToDomainObject(countryInput, country);
        Country updatedCountry = countryService.save(country);
        return countryAssembler.toModel(updatedCountry);
    }

    @DeleteMapping("/{countryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String countryId) {
        countryService.delete(countryId);
    }


}
