package com.apelisser.manager.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String model;
    private String serialNumber;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_id", nullable = false)
    private List<Piece> pieces;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Getter
    @Setter
    @ToString
    @EqualsAndHashCode(of = { "id" })
    @Entity
    @Table(name = "equipment_piece")
    public static class Piece {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private BigDecimal capacity;
        private String description;

    }

}
