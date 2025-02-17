package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.OrganizationModel;
import com.apelisser.manager.domain.model.Organization;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrganizationModelAssembler {

    private final ModelMapper mapper;

    public OrganizationModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public OrganizationModel toModel(Organization organization) {
        return mapper.map(organization, OrganizationModel.class);
    }

    public List<OrganizationModel> toCollectionModel(List<Organization> organizations) {
        return organizations.stream()
            .map(this::toModel)
            .toList();
    }

}
