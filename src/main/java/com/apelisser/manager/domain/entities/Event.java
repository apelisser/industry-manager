package com.apelisser.manager.domain.entities;

import com.apelisser.manager.domain.models.enums.RecordStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String abbreviation;
    private String observation;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "parent_event_id")
    private Event parent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecordStatus status = RecordStatus.ACTIVE;

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isActive() {
        return status == RecordStatus.ACTIVE;
    }

    public boolean isInactive() {
        return status == RecordStatus.INACTIVE;
    }

}
