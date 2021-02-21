package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person {
    private final UUID id;
    private final String email;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("email") String email) {
        this.id = id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }
}
