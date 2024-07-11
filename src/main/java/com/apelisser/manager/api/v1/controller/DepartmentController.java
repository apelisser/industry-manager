package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.mapper.DepartmentInputDisassembler;
import com.apelisser.manager.api.v1.mapper.DepartmentModelAssembler;
import com.apelisser.manager.api.v1.model.DepartmentModel;
import com.apelisser.manager.api.v1.model.input.DepartmentInput;
import com.apelisser.manager.domain.model.Department;
import com.apelisser.manager.domain.service.CompanyRegistrationService;
import com.apelisser.manager.domain.service.DepartmentRegistrationService;
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
    path = "/api/v1/companies/{companyId}/departments",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    private final DepartmentInputDisassembler departmentDisassembler;
    private final DepartmentModelAssembler departmentAssembler;
    private final CompanyRegistrationService companyService;
    private final DepartmentRegistrationService departmentService;

    public DepartmentController(DepartmentInputDisassembler departmentDisassembler,
            DepartmentModelAssembler departmentAssembler, CompanyRegistrationService companyService,
            DepartmentRegistrationService departmentService) {
        this.departmentDisassembler = departmentDisassembler;
        this.departmentAssembler = departmentAssembler;
        this.companyService = companyService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<DepartmentModel> findAll(@PathVariable Long companyId) {
        List<Department> departments = departmentService.findAll(companyId);
        System.out.println(departments);
        return departmentAssembler.toCollectionModel(departments);
    }

    @GetMapping("/{departmentId}")
    public DepartmentModel findById(@PathVariable Long companyId, @PathVariable Long departmentId) {
        Department department = departmentService.findById(companyId, departmentId);
        return departmentAssembler.toModel(department);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentModel add(@PathVariable Long companyId, @RequestBody DepartmentInput departmentInput) {
        Department domainDepartment = departmentDisassembler.toDomainObject(departmentInput);
        domainDepartment.getCompany().setId(companyId);
        Department savedDepartment = departmentService.save(domainDepartment);
        return departmentAssembler.toModel(savedDepartment);
    }

    @PutMapping("/{departmentId}")
    public DepartmentModel update(@PathVariable Long companyId, @PathVariable Long departmentId, @RequestBody DepartmentInput departmentInput) {
        Department department = departmentService.findById(companyId, departmentId);
        departmentDisassembler.copyToDomainObject(departmentInput, department);
        Department updatedDepartment = departmentService.save(department);
        return departmentAssembler.toModel(updatedDepartment);
    }

    @DeleteMapping("/{departmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long companyId, @PathVariable Long departmentId) {
        departmentService.delete(companyId, departmentId);
    }

}
