package com.projeto.contactmanager.model;

import java.util.Objects;

public class Contact {
    private Long id;
    private Person person;
    private Long contact;
    private ContactType contactType;

    public Contact() {

    }
    
    
    
    public Contact(Long id, Person person, Long contact, ContactType contactType) {
        this.id = id;
        this.person = person;
        this.contact = contact;
        this.contactType = contactType;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }



    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        return id == other.id;
    }
    

}
