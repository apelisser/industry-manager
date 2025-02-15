package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.model.Person;

import java.util.List;

public interface PersonRegistrationService {

    Person save(Person person);

    void delete(String personId);

    Person findById(String personId);

    List<Person> findAll();

}
