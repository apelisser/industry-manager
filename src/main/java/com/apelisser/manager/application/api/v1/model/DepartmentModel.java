package com.apelisser.manager.application.api.v1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class DepartmentModel {

    private String id;
    private String name;
    private String description;
    private CompanyResumeModel company;

}
