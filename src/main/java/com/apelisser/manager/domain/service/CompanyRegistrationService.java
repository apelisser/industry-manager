package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Company;

import java.util.List;

public interface CompanyRegistrationService {

    Company save(Company company);

    void delete(Long companyId);

    Company findById(Long companyId);

    List<Company> findAll();

}
