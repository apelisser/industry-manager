package com.apelisser.manager.application.api.v1.model;

import com.apelisser.manager.domain.enums.RecordStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class CompanyModel {

    private Long id;
    private String name;
    private String alias;
    private RecordStatus status;
    private String observation;
    private PersonModel person;

}
