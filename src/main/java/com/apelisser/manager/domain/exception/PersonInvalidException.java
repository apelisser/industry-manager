package com.apelisser.manager.domain.exception;

import com.apelisser.manager.domain.model.enums.PersonType;

import java.io.Serial;

public class PersonInvalidException extends IndustryManagerException {

    @Serial
    private static final long serialVersionUID = 3995201305866419805L;

    private final PersonType type;
    private final String value;

    public PersonInvalidException(PersonType type, String value) {
        this.type = type;
        this.value = value;
    }

    public PersonInvalidException(PersonType type, String value, String message) {
        super(message);
        this.type = type;
        this.value = value;
    }

    public PersonInvalidException(PersonType type, String value, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
        this.value = value;
    }

    public PersonInvalidException(PersonType type, String value, Throwable cause) {
        super(cause);
        this.type = type;
        this.value = value;
    }

    public PersonType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

}
