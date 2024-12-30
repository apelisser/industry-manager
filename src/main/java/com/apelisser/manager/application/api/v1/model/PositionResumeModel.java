package com.apelisser.manager.application.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString
@JsonInclude(NON_NULL)
public class PositionResumeModel {

    private Long id;
    private String name;
    private String description;

}
