package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.mapper.EmployeeInputDisassembler;
import com.apelisser.manager.api.v1.mapper.EmployeeModelAssembler;
import com.apelisser.manager.api.v1.mapper.EmployeeResumeModelAssembler;
import com.apelisser.manager.api.v1.model.EmployeeModel;
import com.apelisser.manager.api.v1.model.EmployeeResumeModel;
import com.apelisser.manager.api.v1.model.input.EmployeeInput;
import com.apelisser.manager.domain.entity.Employee;
import com.apelisser.manager.domain.service.EmployeeRegistrationService;
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
    path = "/api/v1/employees",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeInputDisassembler employeeDisassembler;
    private final EmployeeModelAssembler employeeAssembler;
    private final EmployeeResumeModelAssembler employeeResumeAssembler;
    private final EmployeeRegistrationService employeeService;

    public EmployeeController(EmployeeInputDisassembler employeeDisassembler, EmployeeModelAssembler employeeAssembler,
            EmployeeResumeModelAssembler employeeResumeAssembler, EmployeeRegistrationService employeeService) {
        this.employeeDisassembler = employeeDisassembler;
        this.employeeAssembler = employeeAssembler;
        this.employeeResumeAssembler = employeeResumeAssembler;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResumeModel> findAll() {
        List<Employee> employees = employeeService.findAll();
        return employeeResumeAssembler.toCollectionModel(employees);
    }

    @GetMapping("/{employeeId}")
    public EmployeeModel findById(@PathVariable Long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        return employeeAssembler.toModel(employee);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeModel add(@RequestBody EmployeeInput employeeInput) {
        Employee domainEmployee = employeeDisassembler.toDomainObject(employeeInput);
        Employee savedEmployee = employeeService.save(domainEmployee);
        return employeeAssembler.toModel(savedEmployee);
    }

    @PutMapping("/{employeeId}")
    public EmployeeModel update(@PathVariable Long employeeId, @RequestBody EmployeeInput employeeInput) {
        Employee employee = employeeService.findById(employeeId);
        employeeDisassembler.copyToDomainObject(employeeInput, employee);
        Employee updatedEmployee = employeeService.save(employee);
        return employeeAssembler.toModel(updatedEmployee);
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long employeeId) {
        employeeService.delete(employeeId);
    }

}
