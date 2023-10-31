package com.projeto.contactmanager.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "person")
public class Person {
    
    Long id;
    private String name;
    private String address;
    private String code;
    private String city;
    private String state;

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
        Contact other = (Contact) obj;
        return id == other.getId();
    }
    
    
}
