package com.apelisser.manager.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "equipment")
public class Equipment extends BaseEntity {

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

    public Equipment() {
    }

    public Equipment(String id) {
        super.setId(id);
    }

    @Getter
    @Setter
    @ToString
    @Entity
    @Table(name = "equipment_piece")
    public static class Piece extends BaseEntity {

        private String name;
        private BigDecimal capacity;
        private String description;

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Piece piece = (Piece) o;

            if (super.getId() == null || piece.getId() == null) return false;

            return Objects.equals(super.getId(), piece.getId());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getId());
        }

    }

}
