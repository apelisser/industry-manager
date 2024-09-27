package com.apelisser.manager.api.v1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class EventTypeModel {

    private Long id;
    private String name;
    private String abbreviation;
    private String observation;
    private CompanyResumeModel company;

}
