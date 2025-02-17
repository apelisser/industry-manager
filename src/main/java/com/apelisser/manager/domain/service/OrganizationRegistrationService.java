package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Organization;

import java.util.List;

public interface OrganizationRegistrationService {

    Organization save(Organization organization);

    void delete(String organizationId);

    Organization findById(String organizationId);

    List<Organization> findAll();

    void inactivate(String organizationId);

    void activate(String organizationId);

}
