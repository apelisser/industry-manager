package com.apelisser.manager.domain.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = { "id" })
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String model;
    private String serialNumber;
    private String description;
    
    @ManyToOne(cascade = CascadeType.ALL)
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
    public static class Piece {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private BigDecimal capacity;
        private String description;

    }

}
