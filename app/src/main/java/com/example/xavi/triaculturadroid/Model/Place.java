package com.example.xavi.triaculturadroid.Model;

import java.util.HashSet;

/**
 * Created by demian ${EMAIL}
 * 02/26/18.
 */

public class Place {

    private int id_place;
    private String name;
    private int capacity;
    private String address;

    public HashSet<Request> requests;
    public HashSet<Place_has_Capacity> place_has_capacity;

    public Place(int id, String name, int capacity, String address) {
        this.id_place = id;
        this.name = name;
        this.capacity = capacity;
        this.address = address;
        this.requests = new HashSet<>();
    }

    public Place () {
        this.requests = new HashSet<>();
        this.place_has_capacity = new HashSet<>();
    }

    public int getId_place() {
        return id_place;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
