package com.apelisser.manager.domain.util.validation;

import java.util.function.Predicate;

@FunctionalInterface
public interface Validatable<T> extends Predicate<T> {

}
