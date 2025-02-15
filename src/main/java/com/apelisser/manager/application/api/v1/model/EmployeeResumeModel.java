package com.apelisser.manager.application.api.v1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EmployeeResumeModel {

    private String id;
    private String name;
    private String email;
    private String username;
    private PositionResumeModel position;

}
