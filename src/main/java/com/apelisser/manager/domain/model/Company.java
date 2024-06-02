package com.apelisser.manager.domain.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "departments" })
@EqualsAndHashCode(of = {"id"})
public class Company {

    private Long id;
    private String name;
    private String alias;
    private PersonType personType;
    private String personId;
    private Address address;
    private String description;
    private List<Department> departments;

}
