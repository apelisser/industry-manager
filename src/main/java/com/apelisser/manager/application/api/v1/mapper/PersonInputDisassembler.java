package com.apelisser.manager.application.api.v1.mapper;

import com.apelisser.manager.application.api.v1.model.input.PersonInput;
import com.apelisser.manager.domain.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonInputDisassembler {

    private final ModelMapper mapper;

    public PersonInputDisassembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Person toDomainObject(PersonInput personInput) {
        return mapper.map(personInput, Person.class);
    }

    public void copyToDomainObject(PersonInput personInput, Person person) {
        mapper.map(personInput, person);
    }

}
