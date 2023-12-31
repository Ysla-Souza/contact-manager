package com.projeto.contactmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.contactmanager.model.Person;
import com.projeto.contactmanager.repository.PersonRepository;
import com.projeto.contactmanager.service.DTO.DirectMailDTO;
import com.projeto.contactmanager.service.interfaces.PersonInterface;

@Service
public class PersonService implements PersonInterface{
    
    private PersonRepository repositoryP;

    @Autowired
    public PersonService(PersonRepository repositoryP){
        this.repositoryP = repositoryP;
    }

    @Override
    public Person save (Person person){
        return repositoryP.save(person);
    }

    @Override
    public Optional<Person> getById(Long Id) {
        return repositoryP.findById(Id);
    }

    @Override
    public List<Person> getAll() {
        return repositoryP.findAll();
    }

    @Override
    public Person update(Person person) {
        Optional<Person> updateP = repositoryP.findById(person.getId());
        if (updateP.isPresent()){
            Person newP = updateP.get();
            newP.setName(person.getName());
            newP.setAddress(person.getAddress());
            newP.setCode(person.getCode());
            newP.setCity(person.getCity());
            newP.setState(person.getState());
            return repositoryP.save(newP);
        }
        return person;
    }

    @Override
    public void delete(Long id) {
        repositoryP.deleteById(id);
    }

    public Person getPersonById(Long id){
        Optional<Person> personOP = repositoryP.findById(id);
        return personOP.orElse(null);
    }

    public DirectMailDTO getDirectMailDTO(Long id){
        Optional<Person> personOP = repositoryP.findById(id);
        if(personOP.isPresent()){
            Person person = personOP.get();
            String directMail = person.getAddress() + 
            " - PostalCode: " + person.getCode() +
            " - " + person.getCity() + "/" + person.getState();
            return new DirectMailDTO(person.getId(), person.getName(), directMail);
        }

        return null;
    }
    
}
