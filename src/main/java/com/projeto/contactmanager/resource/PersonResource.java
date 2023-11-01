package com.projeto.contactmanager.resource;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.contactmanager.exceptions.ResourceNotFoundException;
import com.projeto.contactmanager.model.Contact;
import com.projeto.contactmanager.model.Person;
import com.projeto.contactmanager.service.ContactService;
import com.projeto.contactmanager.service.PersonService;
import com.projeto.contactmanager.service.DTO.DirectMailDTO;

@RestController
@RequestMapping("/api/person")
public class PersonResource { // Fazer as exception
    
    private PersonService servicePerson;
    private ContactService serviceContact;

    @Autowired
    public PersonResource(PersonService servicePeron, ContactService ContactService){
        this.servicePerson = servicePerson;
        this.serviceContact = serviceContact;
    }
    @Operation(summary = "Retrieve All People")
    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        List<Person> people = servicePerson.getAll();
        if(people.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(people);
    }
    @Operation(summary = "Create a New Person")
    @PostMapping
    public ResponseEntity<Person> createdPerson(@RequestBody Person person){
        Person newPerson = servicePerson.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
    }
    @Operation(summary = "Retrieve Person Data by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        Person person = servicePerson.getPersonById(id);
        if (person == null)
        return ResponseEntity.notFound().build();
    return ResponseEntity.ok(person); 
    }

    @Operation(summary = "Retrieve Person Data by ID for Direct Mail")
    @GetMapping("/directMail/{id}")
    public ResponseEntity<DirectMailDTO> getDirectMailById(@PathVariable Long id){
        DirectMailDTO directMailDTO = servicePerson.getDirectMailDTO(id);
        if (directMailDTO == null)
        return ResponseEntity.notFound().build();
    return ResponseEntity.ok(directMailDTO);
    }

    @Operation(summary = "Add a New Contact to a Person")
    @GetMapping("/{id}/contacts")
    public ResponseEntity<Person> addContact(@PathVariable Long id, @RequestBody Contact contact){
        Person person = servicePerson.getPersonById(id);
        if (contact.getContactType() == null || contact.getContact() == null){
            return ResponseEntity.badRequest().build();
        }
        if (person == null) {
            throw new ResourceNotFoundException("ID " + id + " Not Found");
        }
        
        contact.setPerson(person);
        Contact newContact = serviceContact.save(contact);
        person.addContact(newContact);
        Person personUpdated = servicePerson.save(person);
        return ResponseEntity.ok(personUpdated);
    }
    @Operation(summary = "List All Contacts of a Person")
    @GetMapping("/{personID}/contacts")
    public ResponseEntity<List<Contact>> getPersonByContact(@PathVariable Long personID){
        Person person = servicePerson.getPersonById(personID);
        if (person == null) {
            throw new ResourceNotFoundException("ID " + personID + " Not Found");
        }
        List<Contact> contacts = person.geContacts();
        
        return ResponseEntity.ok(contacts);
    }
    @Operation(summary = "Update an Existing Person")
    @PutMapping
    public ResponseEntity<Person> update(@RequestBody Person person){
        Person newPerson = servicePerson.update(person);
        if (newPerson == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(newPerson);
    }
    @Operation(summary = "Delete a Person by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        servicePerson.delete(id);
        return ResponseEntity.noContent().build();
    }
}
