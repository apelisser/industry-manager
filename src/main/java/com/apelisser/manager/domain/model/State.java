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
@Table(name = "state")
public class State extends BaseEntity {

    private String name;

    private String abbrevName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
