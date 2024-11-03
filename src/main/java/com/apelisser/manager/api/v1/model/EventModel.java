package com.apelisser.manager.api.v1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" })
public class EventModel {

    private Long id;
    private String name;
    private String abbreviation;
    private String observation;
    private String status;
    private CompanyResumeModel company;
    private EventResumeModel parent;

}
