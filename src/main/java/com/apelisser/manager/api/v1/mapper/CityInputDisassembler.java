package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.input.CityInput;
import com.apelisser.manager.domain.model.City;
import com.apelisser.manager.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CityInputDisassembler {

    private final ModelMapper mapper;

    public CityInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public City toDomainObject(CityInput cityInput) {
        return mapper.map(cityInput, City.class);
    }

    public void copyToDomainObject(CityInput cityInput, City city) {
        city.setState(new State());
        mapper.map(cityInput, city);
    }

}
