package com.projeto.contactmanager.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.projeto.contactmanager.model.Person;
import com.projeto.contactmanager.service.ContactService;
import com.projeto.contactmanager.service.PersonService;

public class PersonResource { // Fazer as exception
    
    private PersonService servicePerson;
    private ContactService serviceContact;

    @Autowired
    public PersonResource(PersonService servicePeron, ContactService ContactService){
        this.servicePerson = servicePerson;
        this.serviceContact = serviceContact;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        List<Person> people = servicePerson.getAll();
        if(people.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(people);

    }
    
    @PostMapping
    public ResponseEntity<Person> createdPerson(@RequestBody Person person){
        Person newPerson = servicePerson.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        Person person = servicePerson.getPersonById(id);
        if (person == null)
        return ResponseEntity.notFound().build();
    return ResponseEntity.ok(person); 
            
    }
}
