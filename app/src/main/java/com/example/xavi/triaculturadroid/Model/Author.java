package com.example.xavi.triaculturadroid.Model;

/**
 * Created by miquel on 2/23/2018.
 */

public class Author {
    private String dni;
    private String name;
    private String surname;
    private String address;

    public Author() {
    }

    public Author(String dni, String name, String surname, String address) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
