package com.apelisser.manager.api.v1.controller;

import com.apelisser.manager.api.v1.mapper.PersonInputDisassembler;
import com.apelisser.manager.api.v1.mapper.PersonModelAssembler;
import com.apelisser.manager.api.v1.mapper.PersonResumeModelAssembler;
import com.apelisser.manager.api.v1.model.PersonModel;
import com.apelisser.manager.api.v1.model.PersonResumeModel;
import com.apelisser.manager.api.v1.model.input.PersonInput;
import com.apelisser.manager.domain.model.Person;
import com.apelisser.manager.domain.service.PersonRegistrationService;
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
    path = "/api/v1/persons",
    produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    private final PersonInputDisassembler personDisassembler;
    private final PersonModelAssembler personAssembler;
    private final PersonResumeModelAssembler personResumeAssembler;
    private final PersonRegistrationService personService;

    public PersonController(PersonInputDisassembler personDisassembler, PersonModelAssembler personAssembler,
        PersonResumeModelAssembler personResumeAssembler, PersonRegistrationService personService) {
        this.personDisassembler = personDisassembler;
        this.personAssembler = personAssembler;
        this.personResumeAssembler = personResumeAssembler;
        this.personService = personService;
    }

    @GetMapping
    public List<PersonResumeModel> findAll() {
        List<Person> persons = personService.findAll();
        return personResumeAssembler.toCollectionModel(persons);
    }

    @GetMapping("/{personId}")
    public PersonModel findById(@PathVariable Long personId) {
        Person person = personService.findById(personId);
        return personAssembler.toModel(person);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResumeModel add(@RequestBody PersonInput personInput) {
        Person domainPerson = personDisassembler.toDomainObject(personInput);
        Person savedPerson = personService.save(domainPerson);
        return personResumeAssembler.toModel(savedPerson);
    }

    @PutMapping("/{personId}")
    public PersonResumeModel update(@PathVariable Long personId, @RequestBody PersonInput personInput) {
        Person person = personService.findById(personId);
        personDisassembler.copyToDomainObject(personInput, person);
        Person updatedPerson = personService.save(person);
        return personResumeAssembler.toModel(updatedPerson);
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long personId) {
        personService.delete(personId);
    }

}
