package com.apelisser.manager.domain.models.validate;

import java.util.function.Predicate;

@FunctionalInterface
public interface Validatable<T> extends Predicate<T> {

}
