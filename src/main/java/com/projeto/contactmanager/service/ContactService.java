package com.projeto.contactmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projeto.contactmanager.model.Contact;
import com.projeto.contactmanager.model.Person;
import com.projeto.contactmanager.repository.ContactRepository;
import com.projeto.contactmanager.repository.PersonRepository;
import com.projeto.contactmanager.service.interfaces.ContactInterface;

@Service
public class ContactService implements ContactInterface{
    
    private ContactRepository repositoryC;
    private PersonRepository repositoryP;


    public ContactService(ContactRepository repositoryC, PersonRepository repositoryP
    ){
        this.repositoryC = repositoryC;
        this.repositoryP = repositoryP;
    }

    @Override
    public Contact save (Long idPerson, Contact contact){
        Optional<Person> personOP = repositoryP.findById(idPerson);
        if (personOP.isPresent()){
        Person person = personOP.get();
        contact.setPerson(person);
        return repositoryC.save(contact);
        }
    else {
        return null;
        }
    }  
    
    @Override
    public Optional<Contact> getById(Long id) {
        return repositoryC.findById(id);
    }

    @Override
    public List<Contact> getAll() {
        return repositoryC.findAll();
    }

    @Override
    public Contact update(Contact contact) {
        Optional<Contact> updateC = repositoryC.findById(contact.getId());
        if (updateC.isPresent()){
            Contact newC = updateC.get();
            newC.setId(contact.getId());
            newC.setContactType(contact.getContactType());
            newC.setContact(contact.getContact());
            newC.setPerson(contact.getPerson());
            return repositoryC.save(newC);
         }
         return contact;
    }

    @Override
    public void delete(Long id) {
        repositoryC.deleteById(id);
    }

    @Override
    public Contact save(Contact contact) {
        return repositoryC.save(contact);
    }
}
