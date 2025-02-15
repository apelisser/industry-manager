package com.apelisser.manager.domain.model;

import com.apelisser.manager.domain.enums.RecordStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company extends BaseEntity {

    private String name;

    private String alias;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Enumerated(EnumType.STRING)
    private RecordStatus status = RecordStatus.ACTIVE;

    private String observation;

}
