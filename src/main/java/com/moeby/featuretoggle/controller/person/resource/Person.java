package com.moeby.featuretoggle.controller.person.resource;

import java.util.Objects;

public record Person(String id, String email) {

    public Person {
        Objects.requireNonNull(id);
        Objects.requireNonNull(email);
    }
}
