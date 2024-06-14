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
    private Person person;
    private Address address;
    private RecordStatus status;
    private String observation;
    private List<Department> departments;

}
