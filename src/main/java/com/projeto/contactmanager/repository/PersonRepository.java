package com.projeto.contactmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.contactmanager.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
