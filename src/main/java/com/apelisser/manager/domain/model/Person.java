package com.apelisser.manager.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class Person {

    @Enumerated(EnumType.STRING)
    private PersonType type;
    
    private String value;
    
}
