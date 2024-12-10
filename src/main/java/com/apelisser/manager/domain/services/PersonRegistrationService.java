package com.apelisser.manager.domain.services;

import com.apelisser.manager.domain.entities.Person;

import java.util.List;

public interface PersonRegistrationService {

    Person save(Person person);

    void delete(Long personId);

    Person findById(Long personId);

    List<Person> findAll();

}
