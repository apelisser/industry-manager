package com.apelisser.manager.domain.model;

import jakarta.persistence.Column;
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
    name = "state",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_state_name_country", columnNames = {"name", "country_id"}),
        @UniqueConstraint(name = "uk_state_abbrev_country", columnNames = {"abbrev_name", "country_id"})
    })
public class State extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 10)
    private String abbrevName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

}
