package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.CountryModel;
import com.apelisser.manager.domain.entity.Country;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryModelAssembler {

    private final ModelMapper mapper;

    public CountryModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CountryModel toModel(Country country) {
        return mapper.map(country, CountryModel.class);
    }

    public List<CountryModel> toCollectionModel(List<Country> countries) {
        return countries.stream()
            .map(this::toModel)
            .toList();
    }

}
