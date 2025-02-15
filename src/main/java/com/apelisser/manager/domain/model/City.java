package com.apelisser.manager.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

}
