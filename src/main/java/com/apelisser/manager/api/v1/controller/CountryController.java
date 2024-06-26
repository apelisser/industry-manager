package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.mapper.CountryInputDisassembler;
import com.apelisser.manager.api.v1.mapper.CountryModelAssembler;
import com.apelisser.manager.api.v1.model.CountryModel;
import com.apelisser.manager.api.v1.model.input.CountryInput;
import com.apelisser.manager.domain.model.Country;
import com.apelisser.manager.domain.service.CountryRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    path = "/v1/countries",
    consumes = MediaType.APPLICATION_JSON_VALUE,
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryModel add(CountryInput country) {
        Country domainCountry = countryDisassembler.toDomainObject(country);
        Country savedCountry = countryService.save(domainCountry);
        return countryAssembler.toModel(savedCountry);
    }

}
