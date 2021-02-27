package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonService(@Qualifier("PersonRepository") PersonDao personDao) {
        this.personDao = personDao;
    }

    @Autowired
    public PersonService(@Qualifier("PersonRepository") PersonDao personDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personDao = personDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<Person> addPerson(Person person) {
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        return personDao.insertPerson(person);
    }

    public List<Person> getAll() {
        return personDao.getAll();
    }

    public Optional<Person> getById(UUID id) {
        return personDao.getById(id);
    }

    public int deleteById(UUID id) {
        return personDao.deleteById(id);
    }

    public Optional<Person> updateById(UUID id, Person person) {
        return personDao.updateById(id, person);
    }
}
