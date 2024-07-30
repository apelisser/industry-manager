package com.apelisser.manager.api.v1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EmployeeModel {

    private Long id;
    private String name;
    private String email;
    private String username;
    private String badge;
    private PositionModel position;

}
