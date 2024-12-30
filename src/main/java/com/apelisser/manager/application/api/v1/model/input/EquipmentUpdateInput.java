package com.apelisser.manager.application.api.v1.model.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EquipmentUpdateInput {

    private String name;
    private String brand;
    private String model;
    private String serialNumber;
    private String description;
    private DepartmentIdInput department;

}
