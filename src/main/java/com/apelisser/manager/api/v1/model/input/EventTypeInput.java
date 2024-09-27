package com.apelisser.manager.api.v1.model.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EventTypeInput {

    private String name;
    private String abbreviation;
    private String observation;
    private CompanyIdInput company;

}
