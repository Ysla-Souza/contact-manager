package com.projeto.contactmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projeto.contactmanager.model.Person;
import com.projeto.contactmanager.repository.PersonRepository;
import com.projeto.contactmanager.service.interfaces.PersonInterface;

@Service
public class PersonService implements PersonInterface{
    
    private PersonRepository repositoryP;

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

    
}
