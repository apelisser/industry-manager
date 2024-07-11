package com.apelisser.manager.domain.model;

import com.apelisser.manager.domain.enums.RecordStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String alias;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Enumerated(EnumType.STRING)
    private RecordStatus status = RecordStatus.ACTIVE;

    private String observation;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();

}
