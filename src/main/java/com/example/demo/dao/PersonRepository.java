package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("PersonRepository")
public class PersonRepository implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public Optional<Person> insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getEmail()));
        return getById(id);
    }

    @Override
    public List<Person> getAll() {
        return DB;
    }

    @Override
    public Optional<Person> getById(UUID id) {
        return DB.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Person> updateById(UUID id, Person person) {
        return getById(id)
                .map(item -> {
                    int index = DB.indexOf(item);
                    System.out.println(index);
                    if (index >= 0) {
                        DB.set(index, new Person(id, person.getEmail()));
                        return DB.get(index);
                    }
                    return item;
                });
    }

    @Override
    public int deleteById(UUID id) {
        Optional<Person> person = getById(id);
        if (person.isEmpty()) {
            return 0;
        }
        DB.remove(person.get());
        return 1;
    }
}
