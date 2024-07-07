package com.apelisser.manager.api.v1.model;

import com.apelisser.manager.domain.enums.PersonType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class PersonResumeModel {

    private Long id;
    private PersonType type;
    private String code;

}
