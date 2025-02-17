package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.enums.RecordStatus;
import com.apelisser.manager.domain.exception.ConstraintViolationException;
import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.model.Organization;
import com.apelisser.manager.domain.repository.OrganizationRepository;
import com.apelisser.manager.domain.service.OrganizationRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationRegistrationServiceImpl implements OrganizationRegistrationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationRegistrationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization save(Organization organization) {
        try {
            return organizationRepository.save(organization);
        } catch (DataIntegrityViolationException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public void delete(String organizationId) {
        try {
            organizationRepository.deleteById(organizationId);
            organizationRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(Organization.class, organizationId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(Organization.class, organizationId, e);
        }
    }

    @Override
    public Organization findById(String organizationId) {
        return organizationRepository.findById(organizationId)
            .orElseThrow(() -> new EntityNotFoundException(Organization.class, organizationId));
    }

    @Override
    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public void inactivate(String organizationId) {
        Organization organization = findById(organizationId);
        if (!organization.isInactive()) {
            organization.setStatus(RecordStatus.INACTIVE);
            organizationRepository.save(organization);
        }
    }

    @Override
    public void activate(String organizationId) {
        Organization organization = findById(organizationId);
        if (!organization.isActive()) {
            organization.setStatus(RecordStatus.ACTIVE);
            organizationRepository.save(organization);
        }
    }

}
