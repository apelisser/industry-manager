package com.apelisser.manager.domain.model;

import com.apelisser.manager.domain.enums.RecordStatus;
import jakarta.persistence.Column;
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
@Table(name = "event")
public class Event extends BaseEntity {

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

    public Event() {
    }

    public Event(String id) {
        super.setId(id);
    }

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
