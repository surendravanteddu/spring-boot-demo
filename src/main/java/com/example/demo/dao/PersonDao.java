package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    Optional<Person> insertPerson (UUID id, Person person);

    default Optional<Person> insertPerson (Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> getAll();

    Optional<Person> getById(UUID id);

    Optional<Person> updateById(UUID id, Person person);

    int deleteById(UUID id);
}
