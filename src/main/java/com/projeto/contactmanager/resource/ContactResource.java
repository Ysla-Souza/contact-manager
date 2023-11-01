package com.projeto.contactmanager.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.contactmanager.model.Contact;
import com.projeto.contactmanager.service.ContactService;
import com.projeto.contactmanager.service.PersonService;

@RestController
@RequestMapping("/contacts")
public class ContactResource { // Fazer as exception

    private ContactService contactService;
    private PersonService personService;

    public ContactResource(ContactService contactService, PersonService personService) {
        this.contactService = contactService;
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContact(){
        List<Contact> contatcs = contactService.getAll();
        return ResponseEntity.ok(contatcs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Long id) {
        Contact contact = contactService.getById(id).orElse(null);
        return ResponseEntity.ok(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@PathVariable Long id, @RequestBody Contact updateC){
        Contact contactExist = contactService.getById(id).orElse(null);
        contactExist.setContactType(updateC.getContactType());
        contactExist.setContact(updateC.getContact());
        Contact contactSave = contactService.update(contactExist);
        return ResponseEntity.ok(contactSave);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        contactService.delete(id);
        return ResponseEntity.noContent().build();
    }

}