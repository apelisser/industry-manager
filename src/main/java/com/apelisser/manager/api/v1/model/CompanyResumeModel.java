package com.apelisser.manager.api.v1.model;

import com.apelisser.manager.domain.enums.RecordStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
public class CompanyResumeModel {

    private Long id;
    private String name;
    private RecordStatus status;
    private PersonModel person;

}
