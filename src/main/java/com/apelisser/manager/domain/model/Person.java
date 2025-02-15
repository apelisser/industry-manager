package com.apelisser.manager.domain.model;

import com.apelisser.manager.domain.enums.PersonType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private PersonType type;

    private String code;

    public boolean isValid() {
        return type != null && type.isValid(code);
    }

}
