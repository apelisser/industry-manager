package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Company;

import java.util.List;

public interface CompanyRegistrationService {

    Company save(Company company);

    void delete(String companyId);

    Company findById(String companyId);

    List<Company> findAll();

}
