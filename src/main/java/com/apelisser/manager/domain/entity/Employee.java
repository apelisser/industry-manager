package com.apelisser.manager.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Employee(Long id) {
        this.id = id;
    }

}
