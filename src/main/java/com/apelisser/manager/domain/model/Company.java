package com.apelisser.manager.domain.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = { "departments" })
@EqualsAndHashCode(of = {"id"})
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String alias;
    
    @Embedded
    private Person person;
    
    @Enumerated(EnumType.STRING)
    private RecordStatus status;
    
    private String observation;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Address> adresses = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private List<Department> departments = new ArrayList<>();

}
