package com.example.xavi.triaculturadroid.Model;

import java.util.HashSet;

/**
 * Created by demian ${EMAIL}
 * 02/26/18.
 */

public class Capacity {
    public Capacity()
    {
        this.place_has_capacity = new HashSet<Place_has_Capacity>();
    }

    public int id_capacity;
    public String type_spectacle;

    public HashSet<Place_has_Capacity> place_has_capacity;

}
