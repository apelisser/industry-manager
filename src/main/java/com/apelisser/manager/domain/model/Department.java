package com.apelisser.manager.domain.model;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" })
public class Department {

    private Long id;
    private String name;
    private String description;
    private List<Equipment> equipments;

}
