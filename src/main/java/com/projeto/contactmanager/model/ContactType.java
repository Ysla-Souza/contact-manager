package com.projeto.contactmanager.model;

public enum ContactType {

    Home(0),
    Smartphone(1),
    Email(3);

    private int value;

    ContactType(int value){
        this.value = value;
    }


}
