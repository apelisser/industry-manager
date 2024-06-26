package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.input.CountryInput;
import com.apelisser.manager.domain.model.Country;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CountryInputDisassembler {

    private final ModelMapper mapper;

    public CountryInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Country toDomainObject(CountryInput countryInput) {
        return mapper.map(countryInput, Country.class);
    }

    public void copyToDomainObject(CountryInput countryInput, Country country) {
        mapper.map(countryInput, country);
    }

}
