package com.apelisser.manager.domain.model;

import com.github.f4b6a3.ulid.UlidCreator;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(length = 26)
    private String id;

    @PrePersist
    protected void prePersist() {
        if (id == null) {
            id = UlidCreator.getUlid().toString();
        }
    }

}
