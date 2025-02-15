package com.apelisser.manager.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
