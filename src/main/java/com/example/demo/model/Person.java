package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person {
    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private UUID id;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("email") String email) {
        this.id = id;
        this.email = email;
    }

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }
}
