package com.example.xavi.triaculturadroid.Model;

import java.util.HashSet;

/**
 * Created by demian ${EMAIL}
 * 02/26/18.
 */

public class Capacity {
    public Capacity()
    {
        this.place_has_capacity = new HashSet<>();
    }

    private int id_capacity;
    private String type_spectacle;

    public HashSet<Place_has_Capacity> place_has_capacity;

    public int getId_capacity() {
        return id_capacity;
    }

    public void setId_capacity(int id_capacity) {
        this.id_capacity = id_capacity;
    }

    public String getType_spectacle() {
        return type_spectacle;
    }

    public void setType_spectacle(String type_spectacle) {
        this.type_spectacle = type_spectacle;
    }
}
