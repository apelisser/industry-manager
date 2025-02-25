package com.apelisser.manager.domain.service.impl;

import com.apelisser.manager.domain.exception.EntityInUseException;
import com.apelisser.manager.domain.exception.EntityNotFoundException;
import com.apelisser.manager.domain.exception.PersonInvalidException;
import com.apelisser.manager.domain.model.Person;
import com.apelisser.manager.domain.repository.PersonRepository;
import com.apelisser.manager.domain.service.PersonRegistrationService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonRegistrationServiceImpl implements PersonRegistrationService {

    private final PersonRepository personRepository;

    public PersonRegistrationServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        if (!person.isValid()) {
            throw new PersonInvalidException(person.getType(), person.getCode());
        }
        return personRepository.save(person);
    }

    @Override
    public void delete(String personId) {
        try {
            personRepository.deleteById(personId);
            personRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(Person.class, personId, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(Person.class, personId, e);
        }
    }

    @Override
    public Person findById(String personId) {
        return personRepository.findById(personId)
            .orElseThrow(() -> new EntityNotFoundException(Person.class, personId));
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

}
