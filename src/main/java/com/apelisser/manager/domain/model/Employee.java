package com.apelisser.manager.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "password", "superior" })
@EqualsAndHashCode(of = { "id" })
public class Employee {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String badgeId;
    private String position;
    private Employee superior;

}
