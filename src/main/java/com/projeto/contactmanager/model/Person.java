package com.projeto.contactmanager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name = "person")
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String address;

    @Column
    private String code;

    @Column
    private String city;

    @Column
    private String state;

    @OneToMany(mappedBy =  "person", cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<Contact> contact;
    @JsonIgnore

    public Person (){

    }

    public Person(Long id, String name, String address, String code, String city, String state) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.code = code;
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
    
    public List<Contact> geContacts(){
        return contact;
    }

    public void addContact(Contact newContact) {
        if (contact == null){
            contact = new ArrayList<>();
        }

        contact.add(newContact);
        newContact.setPerson(this);
    }
}
