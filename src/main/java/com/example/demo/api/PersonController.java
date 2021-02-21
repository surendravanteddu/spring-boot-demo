package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person).orElse(null);
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping(path = "id/{id}")
    public Person getById(@PathVariable("id") UUID id) {
        return personService.getById(id).orElse(null);
    }

    @DeleteMapping(path = "id/{id}")
    public int deleteById(@PathVariable("id") UUID id) {
        return personService.deleteById(id);
    }

    @PutMapping(path = "id/{id}")
    public Person updateById(@PathVariable("id") UUID id, @RequestBody Person person) {
        return personService.updateById(id, person).orElse(null);
    }
}
