package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.CompanyResumeModel;
import com.apelisser.manager.domain.model.Company;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyResumeModelAssembler {

    private final ModelMapper mapper;

    public CompanyResumeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CompanyResumeModel toModel(Company company) {
        return mapper.map(company, CompanyResumeModel.class);
    }

    public List<CompanyResumeModel> toCollectionModel(List<Company> companies) {
        return companies.stream()
            .map(this::toModel)
            .toList();
    }

}
