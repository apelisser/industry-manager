package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.PersonModel;
import com.apelisser.manager.domain.entities.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonModelAssembler {

    private final ModelMapper mapper;

    public PersonModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PersonModel toModel(Person person) {
        return mapper.map(person, PersonModel.class);
    }

    public List<PersonModel> toCollectionModel(List<Person> persons) {
        return persons.stream()
            .map(this::toModel)
            .toList();
    }

}
