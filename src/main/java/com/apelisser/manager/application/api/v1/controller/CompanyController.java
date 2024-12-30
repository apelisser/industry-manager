package com.apelisser.manager.application.api.v1.controller;

import com.apelisser.manager.application.api.v1.mapper.CompanyInputDisassembler;
import com.apelisser.manager.application.api.v1.mapper.CompanyModelAssembler;
import com.apelisser.manager.application.api.v1.model.CompanyModel;
import com.apelisser.manager.application.api.v1.model.input.CompanyInput;
import com.apelisser.manager.domain.model.Company;
import com.apelisser.manager.domain.service.CompanyRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
    path = "/api/v1/companies",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

    private final CompanyInputDisassembler companyDisassembler;
    private final CompanyModelAssembler companyAssembler;
    private final CompanyRegistrationService companyService;

    public CompanyController(CompanyInputDisassembler companyDisassembler, CompanyModelAssembler companyAssembler,
        CompanyRegistrationService companyService) {
        this.companyDisassembler = companyDisassembler;
        this.companyAssembler = companyAssembler;
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyModel> findAll() {
        List<Company> companies = companyService.findAll();
        return companyAssembler.toCollectionModel(companies);
    }

    @GetMapping("/{companyId}")
    public CompanyModel findById(@PathVariable Long companyId) {
        Company company = companyService.findById(companyId);
        return companyAssembler.toModel(company);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyModel add(@RequestBody CompanyInput companyInput) {
        Company domainCompany = companyDisassembler.toDomainObject(companyInput);
        Company savedCompany = companyService.save(domainCompany);
        return companyAssembler.toModel(savedCompany);
    }

    @PutMapping("/{companyId}")
    public CompanyModel update(@PathVariable Long companyId, @RequestBody CompanyInput companyInput) {
        Company company = companyService.findById(companyId);
        companyDisassembler.copyToDomainObject(companyInput, company);
        Company updatedCompany = companyService.save(company);
        return companyAssembler.toModel(updatedCompany);
    }

    @DeleteMapping("/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long companyId) {
        companyService.delete(companyId);
    }

}
