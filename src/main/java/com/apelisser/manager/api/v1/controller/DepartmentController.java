package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.mapper.DepartmentInputDisassembler;
import com.apelisser.manager.api.v1.mapper.DepartmentModelAssembler;
import com.apelisser.manager.api.v1.mapper.DepartmentResumeModelAssembler;
import com.apelisser.manager.api.v1.model.DepartmentModel;
import com.apelisser.manager.api.v1.model.DepartmentResumeModel;
import com.apelisser.manager.api.v1.model.input.DepartmentInput;
import com.apelisser.manager.domain.entity.Department;
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
    path = "/api/v1/departments",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    private final DepartmentInputDisassembler departmentDisassembler;
    private final DepartmentModelAssembler departmentAssembler;
    private final DepartmentResumeModelAssembler departmentResumeAssembler;
    private final DepartmentRegistrationService departmentService;

    public DepartmentController(DepartmentInputDisassembler departmentDisassembler,
            DepartmentModelAssembler departmentAssembler, DepartmentResumeModelAssembler departmentResumeAssembler,
            DepartmentRegistrationService departmentService) {
        this.departmentDisassembler = departmentDisassembler;
        this.departmentAssembler = departmentAssembler;
        this.departmentResumeAssembler = departmentResumeAssembler;
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<DepartmentResumeModel> findAll() {
        List<Department> departments = departmentService.findAll();
        return departmentResumeAssembler.toCollectionModel(departments);
    }

    @GetMapping("/{departmentId}")
    public DepartmentModel findById(@PathVariable Long departmentId) {
        Department department = departmentService.findById(departmentId);
        return departmentAssembler.toModel(department);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentModel add(@RequestBody DepartmentInput departmentInput) {
        Department domainDepartment = departmentDisassembler.toDomainObject(departmentInput);
        Department savedDepartment = departmentService.save(domainDepartment);
        return departmentAssembler.toModel(savedDepartment);
    }

    @PutMapping("/{departmentId}")
    public DepartmentModel update(@PathVariable Long departmentId, @RequestBody DepartmentInput departmentInput) {
        Department department = departmentService.findById(departmentId);
        departmentDisassembler.copyToDomainObject(departmentInput, department);
        Department updatedDepartment = departmentService.save(department);
        return departmentAssembler.toModel(updatedDepartment);
    }

    @DeleteMapping("/{departmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long departmentId) {
        departmentService.delete(departmentId);
    }

}
