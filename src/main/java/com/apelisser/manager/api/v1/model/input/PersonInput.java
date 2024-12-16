package com.apelisser.manager.api.v1.model.input;

import com.apelisser.manager.domain.model.enums.PersonType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PersonInput {

    private PersonType type;
    private String code;

}
