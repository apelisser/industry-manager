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
@Table(name = "department")
public class Department extends BaseEntity {

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
