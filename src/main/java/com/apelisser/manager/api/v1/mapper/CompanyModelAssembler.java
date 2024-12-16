package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.CompanyModel;
import com.apelisser.manager.domain.entity.Company;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyModelAssembler {

    private final ModelMapper mapper;

    public CompanyModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CompanyModel toModel(Company company) {
        return mapper.map(company, CompanyModel.class);
    }

    public List<CompanyModel> toCollectionModel(List<Company> companies) {
        return companies.stream()
            .map(this::toModel)
            .toList();
    }

}
