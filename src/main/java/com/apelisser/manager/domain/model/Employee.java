package com.apelisser.manager.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String badge;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    public Employee() {
    }

    public Employee(String id) {
        super.setId(id);
    }

}
