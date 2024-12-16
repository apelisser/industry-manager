package com.apelisser.manager.domain.model.validate;

import java.util.function.Predicate;

@FunctionalInterface
public interface Validatable<T> extends Predicate<T> {

}
