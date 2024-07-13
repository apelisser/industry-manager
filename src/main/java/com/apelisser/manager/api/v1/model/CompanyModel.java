package com.apelisser.manager.api.v1.model;

import com.apelisser.manager.domain.enums.RecordStatus;
import com.apelisser.manager.domain.model.Address;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
    private List<Address> addresses = new ArrayList<>();

}
