package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("PersonRepository")
public class PersonRepository implements PersonDao {
    private static List<Person> DB = new ArrayList<>();
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Person> insertPerson(UUID id, Person person) {
        jdbcTemplate.update("insert into book (id, email) values (?, ?)", id, person.getEmail());
        return getById(id);
    }

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("select * from book",
                (resultSet, i ) -> new Person(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("email")
                )
        );
    }

    @Override
    public Optional<Person> getById(UUID id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "select * from book where id = ?",
                (resultSet, i ) -> new Person(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getString("email")
                ),
                id.toString()
        ));
    }

    @Override
    public Optional<Person> updateById(UUID id, Person person) {
        jdbcTemplate.update("update book set email = ? where id = ?", person.getEmail(), id.toString());
        return getById(id);
    }

    @Override
    public int deleteById(UUID id) {
        return jdbcTemplate.update("delete from book where id = ?", id.toString());
    }
}
