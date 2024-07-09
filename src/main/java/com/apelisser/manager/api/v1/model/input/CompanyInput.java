package com.apelisser.manager.api.v1.model.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CompanyInput {

    private String name;
    private String alias;
    private PersonIdInput person;
    private String observation;

}
