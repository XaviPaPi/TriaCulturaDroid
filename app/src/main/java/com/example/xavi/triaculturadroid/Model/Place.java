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
        this.requests = new HashSet<Request>();
    }

    public Place () {
        this.requests = new HashSet<Request>();
        this.place_has_capacity = new HashSet<Place_has_Capacity>();
    }

}
