package com.apelisser.manager.api.v1.model;

import com.apelisser.manager.domain.model.Company;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class PersonModel extends PersonResumeModel{

    List<Company> companies;

}
