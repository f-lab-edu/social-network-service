package com.zudbs.project.util;

public enum SessionKey {

    NONE(""),
    LOGIN_USER_ID("userId");

    final private String key;

    private SessionKey(String key) {
        this.key = key;
    }

    public String getkey() {
        return key;
    }
}
