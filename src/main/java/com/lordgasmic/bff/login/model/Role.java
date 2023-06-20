package com.lordgasmic.bff.login.model;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum Role {
    user(1),
    feeding(2);

    private final int permission;

    public static Role fromValue(final int value) {
        final Optional<Role> role = Arrays.stream(Role.values()).filter(r -> r.permission == value).findFirst();

        return role.orElse(null);
    }

    public static boolean hasRole(final Role role, final int value) {
        return ((role.permission & value) == role.permission);
    }
}
