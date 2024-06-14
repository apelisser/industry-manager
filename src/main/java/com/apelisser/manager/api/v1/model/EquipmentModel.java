package com.apelisser.manager.api.v1.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" })
public class EquipmentModel {

    private Long id;
    private String name;
    private String description;
    private List<Piece> pieces;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode(of = { "id" })
    public class Piece {
        private Long id;
        private String na5me;
        private double capacity;
        private String description;
    }
}
