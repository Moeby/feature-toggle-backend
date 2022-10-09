package com.moeby.featuretoggle.service.common;

import java.util.Arrays;

public enum UserRole {
    ADMIN(1), USER(2);

    private final int key;

    UserRole(int key) {
        this.key = key;
    }

    public static UserRole getUserRoleForKey(int key) {
        return Arrays.stream(UserRole.values())
                .filter(userRole -> userRole.key == key)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No user role present for key: " + key));
    }
}
