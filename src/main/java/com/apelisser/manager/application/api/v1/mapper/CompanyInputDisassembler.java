package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.input.CompanyInput;
import com.apelisser.manager.domain.model.Company;
import com.apelisser.manager.domain.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyInputDisassembler {

    private final ModelMapper mapper;

    public CompanyInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Company toDomainObject(CompanyInput companyInput) {
        return mapper.map(companyInput, Company.class);
    }

    public void copyToDomainObject(CompanyInput companyInput, Company company) {
        company.setPerson(new Person());
        mapper.map(companyInput, company);
    }

}
