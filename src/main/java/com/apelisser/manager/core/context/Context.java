package com.apelisser.manager.core.context;

import java.util.Map;
import java.util.Optional;

public interface Context {

    void add(ContextKey key, String value);

    Optional<String> get(ContextKey key);

    Optional<Map<ContextKey, String>> getAll();

    void addAll(Map<ContextKey, String> contextProps);

    boolean exists(ContextKey key);

    void remove(ContextKey key);

    void clear();

}
