package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.CityModel;
import com.apelisser.manager.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityModelAssembler {

    private final ModelMapper mapper;

    public CityModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CityModel toModel(City city) {
        return mapper.map(city, CityModel.class);
    }

    public List<CityModel> toCollectionModel(List<City> cities) {
        return cities.stream()
            .map(this::toModel)
            .toList();
    }

}
