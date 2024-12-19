package com.apelisser.manager.core.context;

public enum ContextKey {

    REQUEST_ID("requestId");

    private final String key;

    ContextKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
