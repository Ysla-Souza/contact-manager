package com.projeto.contactmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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
        
        }
    }  
    
    @Override
    public Optional<Contact> getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<Contact> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Contact save(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
