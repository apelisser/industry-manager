package com.apelisser.manager.application.api.v1.model.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DepartmentInput {

    private String name;
    private String description;
    private CompanyIdInput company;

}