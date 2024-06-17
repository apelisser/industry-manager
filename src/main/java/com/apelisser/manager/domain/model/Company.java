package com.apelisser.manager.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    
    private Person person;
    
    @Enumerated(EnumType.STRING)
    private RecordStatus status;
    
    private String observation;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Address> address = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private List<Department> departments = new ArrayList<>();

}
