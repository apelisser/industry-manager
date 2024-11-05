package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.Company;

import java.util.List;

public interface CompanyRegistrationService {

    Company save(Company company);

    void delete(Long companyId);

    Company findById(Long companyId);

    List<Company> findAll();

}
