package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.input.OrganizationInput;
import com.apelisser.manager.domain.model.Organization;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrganizationInputDisassembler {

    private final ModelMapper mapper;

    public OrganizationInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Organization toDomainObject(OrganizationInput organizationInput) {
        return mapper.map(organizationInput, Organization.class);
    }

    public void copyToDomainObject(OrganizationInput organizationInput, Organization organization) {
        mapper.map(organizationInput, organization);
    }

}
