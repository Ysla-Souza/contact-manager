package com.projeto.contactmanager.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.projeto.contactmanager.model.Person;

public interface PersonInterface {
        Person save (Person person);
        Optional<Person> getById(Long Id);
        List <Person> getAll();
        Person update(Person person);
        void delete(Long idLong);
    }

