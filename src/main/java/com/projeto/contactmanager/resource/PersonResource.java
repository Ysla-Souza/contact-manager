package com.projeto.contactmanager.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.projeto.contactmanager.model.Person;
import com.projeto.contactmanager.service.PersonService;

public class PersonResource {
    
    private PersonService servicePerson;

    @Autowired
    public PersonResource(PersonService servicePeron){
        this.servicePerson = servicePerson;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        List<Person> people = servicePerson.getAll();
        if(people.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(people);

    }
}
