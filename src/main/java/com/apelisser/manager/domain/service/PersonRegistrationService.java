package com.apelisser.manager.domain.service;

import com.apelisser.manager.domain.entity.Person;

import java.util.List;

public interface PersonRegistrationService {

    Person save(Person person);

    void delete(Long personId);

    Person findById(Long personId);

    List<Person> findAll();

}
