package com.apelisser.manager.api.v1.model.input;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EquipmentInput {
    
    private CompanyIdInput company;
    private String name;
    private String brand;
    private String model;
    private String serialNumber;
    private String description;
    private List<Piece> pieces;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode
    public class Piece {
        private String name;
        private double capacity;
        private String description;
    }
}
