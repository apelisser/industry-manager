package com.apelisser.manager.api.v1.mapper;

import com.apelisser.manager.api.v1.model.PersonResumeModel;
import com.apelisser.manager.domain.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonResumeModelAssembler {

    private final ModelMapper mapper;

    public PersonResumeModelAssembler(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PersonResumeModel toModel(Person person) {
        return mapper.map(person, PersonResumeModel.class);
    }

    public List<PersonResumeModel> toCollectionModel(List<Person> persons) {
        return persons.stream()
            .map(this::toModel)
            .toList();
    }

}
