package com.apelisser.manager.core.context.impl;

import com.apelisser.manager.core.context.Context;
import com.apelisser.manager.core.context.ContextKey;
import com.apelisser.manager.domain.util.Assert;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

@Component
public class ContextImpl implements Context {

    @Override
    public void add(ContextKey key, String value) {
        validateKey(key);
        MDC.put(key.getKey(), value);
    }

    @Override
    public Optional<String> get(ContextKey key) {
        validateKey(key);
        return Optional.ofNullable(MDC.get(key.getKey()));
    }

    @Override
    public Optional<Map<ContextKey, String>> getAll() {
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();

        if (copyOfContextMap == null) {
            return Optional.empty();
        }

        Map<ContextKey, String> enumMap = new EnumMap<>(ContextKey.class);
        for (ContextKey key : ContextKey.values()) {
            String value = copyOfContextMap.get(key.getKey());
            if (value != null) {
                enumMap.put(key, value);
            }
        }

        return enumMap.isEmpty() ?
            Optional.empty() :
            Optional.of(enumMap);
    }

    @Override
    public void addAll(Map<ContextKey, String> contextProps) {
        if (contextProps != null) {
            contextProps.forEach(this::add);
        }
    }

    @Override
    public boolean exists(ContextKey key) {
        validateKey(key);
        return MDC.get(key.getKey()) != null;
    }

    @Override
    public void remove(ContextKey key) {
        if (key != null) {
            MDC.remove(key.getKey());
        }
    }

    @Override
    public void clear() {
        MDC.clear();
    }

    private void validateKey(ContextKey key) {
        Assert.notNull(key, "Key cannot be null.");
    }

}
