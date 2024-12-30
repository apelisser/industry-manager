package com.apelisser.manager.application.api.v1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" })
public class EquipmentResumeModel {

    private Long id;
    private String name;
    private String brand;
    private String model;
    private List<Piece> pieces;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode(of = { "id" })
    public static class Piece {
        private Long id;
        private String name;
        private double capacity;
    }
}
