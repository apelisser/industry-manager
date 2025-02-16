package com.apelisser.manager.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "city",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_name_state", columnNames = {"name", "state_id"})
    })
public class City extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

}
