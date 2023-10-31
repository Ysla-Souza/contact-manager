package com.projeto.contactmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.contactmanager.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
    
}
