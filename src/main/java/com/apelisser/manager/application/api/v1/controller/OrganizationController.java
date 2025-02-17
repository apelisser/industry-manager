package com.apelisser.manager.application.api.v1.controller;

import com.apelisser.manager.application.api.v1.mapper.OrganizationInputDisassembler;
import com.apelisser.manager.application.api.v1.mapper.OrganizationModelAssembler;
import com.apelisser.manager.application.api.v1.model.OrganizationModel;
import com.apelisser.manager.application.api.v1.model.input.OrganizationInput;
import com.apelisser.manager.domain.model.Organization;
import com.apelisser.manager.domain.service.OrganizationRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
    path = "/api/v1/organizations",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationInputDisassembler organizationDisassembler;
    private final OrganizationModelAssembler organizationAssembler;
    private final OrganizationRegistrationService organizationService;

    public OrganizationController(OrganizationInputDisassembler organizationDisassembler,
            OrganizationModelAssembler organizationAssembler, OrganizationRegistrationService organizationService) {
        this.organizationDisassembler = organizationDisassembler;
        this.organizationAssembler = organizationAssembler;
        this.organizationService = organizationService;
    }

    @GetMapping
    public List<OrganizationModel> findAll() {
        List<Organization> organizations = organizationService.findAll();
        return organizationAssembler.toCollectionModel(organizations);
    }

    @GetMapping("/{organizationId}")
    public OrganizationModel findById(@PathVariable String organizationId) {
        Organization organization = organizationService.findById(organizationId);
        return organizationAssembler.toModel(organization);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationModel add(@RequestBody OrganizationInput organizationInput) {
        Organization domainOrganization = organizationDisassembler.toDomainObject(organizationInput);
        Organization savedOrganization = organizationService.save(domainOrganization);
        return organizationAssembler.toModel(savedOrganization);
    }

    @PutMapping("/{organizationId}")
    public OrganizationModel update(@PathVariable String organizationId,
            @RequestBody OrganizationInput organizationInput) {
        Organization organization = organizationService.findById(organizationId);
        organizationDisassembler.copyToDomainObject(organizationInput, organization);
        Organization updatedOrganization = organizationService.save(organization);
        return organizationAssembler.toModel(updatedOrganization);
    }

    @DeleteMapping("/{organizationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String organizationId) {
        organizationService.delete(organizationId);
    }

    @PatchMapping("/{organizationId}/inactivation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivate(@PathVariable String organizationId) {
        organizationService.inactivate(organizationId);
    }

    @PatchMapping("/{organizationId}/activation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@PathVariable String organizationId) {
        organizationService.activate(organizationId);
    }

}
