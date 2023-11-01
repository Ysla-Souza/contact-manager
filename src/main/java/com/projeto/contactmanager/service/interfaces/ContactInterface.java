package com.projeto.contactmanager.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.projeto.contactmanager.model.Contact;

public interface ContactInterface {
    
    Contact save (Long idPerson, Contact contact);
    Optional<Contact> getById(Long id);
    List<Contact> getAll();
    Contact update (Contact contact);
    void delete (Long id);
    Contact save (Contact contact);
}
