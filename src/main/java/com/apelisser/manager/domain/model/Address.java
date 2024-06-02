package com.apelisser.manager.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
    
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String zipCode;

}
