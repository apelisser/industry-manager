package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.model.Company;
import com.apelisser.manager.domain.model.Person;
import com.apelisser.manager.domain.repository.CompanyRepository;
import com.apelisser.manager.domain.service.CompanyRegistrationService;
import com.apelisser.manager.domain.service.PersonRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyRegistrationServiceImpl implements CompanyRegistrationService {

    private final CompanyRepository companyRepository;
    private final PersonRegistrationService personService;

    public CompanyRegistrationServiceImpl(CompanyRepository companyRepository, PersonRegistrationService personService) {
        this.companyRepository = companyRepository;
        this.personService = personService;
    }

    @Override
    public Company save(Company company) {
        Long personId = company.getPerson().getId();
        Person person = personService.findById(personId);
        company.setPerson(person);
        return companyRepository.save(company);
    }

    @Override
    public void delete(Long companyId) {
        try {
            companyRepository.deleteById(companyId);
            companyRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(Company.class, companyId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(Company.class, companyId, e);
        }
    }

    @Override
    public Company findById(Long companyId) {
        return companyRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException(Company.class, companyId));
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

}
