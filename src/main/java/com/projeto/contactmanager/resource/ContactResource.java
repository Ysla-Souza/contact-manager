package com.projeto.contactmanager.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.contactmanager.exceptions.ResourceNotFoundException;
import com.projeto.contactmanager.model.Contact;
import com.projeto.contactmanager.service.ContactService;
import com.projeto.contactmanager.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/contacts")
public class ContactResource { 

    private ContactService contactService;

    @Autowired
    public ContactResource(ContactService contactService, PersonService personService) {
        this.contactService = contactService;
    }
    @Operation(summary = "List All Contacts")
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContact(){
        List<Contact> contatcs = contactService.getAll();
        return ResponseEntity.ok(contatcs);
    }
    @Operation(summary = "Retrieve Contact Data by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Long id) {
        Contact contact = contactService.getById(id).orElseThrow(() -> new ResourceNotFoundException("ID " + id + " Not Found"));
        return ResponseEntity.ok(contact);
    }
    @Operation(summary = "Update an Existing Contact")
    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@PathVariable Long id, @RequestBody Contact updateC){
        Contact contactExist = contactService.getById(id).orElseThrow(() -> new ResourceNotFoundException("ID " + id + " Not Found"));
        contactExist.setContactType(updateC.getContactType());
        contactExist.setContact(updateC.getContact());
        Contact contactSave = contactService.update(contactExist);
        return ResponseEntity.ok(contactSave);

    }
    @Operation(summary = "Delete a Contact by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Contact contactDelete = contactService.getById(id).orElseThrow(() -> new ResourceNotFoundException("ID " + id + " Not Found"));
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }

}