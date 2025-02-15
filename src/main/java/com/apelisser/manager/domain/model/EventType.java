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
@Table(name = "event_type")
public class EventType extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String abbreviation;
    private String observation;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

}
