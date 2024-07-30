package com.apelisser.manager.api.v1.model.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EmployeeInput {

    private String name;
    private String email;
    private String username;
    private String password;
    private String badge;
    private PositionIdInput position;

}
