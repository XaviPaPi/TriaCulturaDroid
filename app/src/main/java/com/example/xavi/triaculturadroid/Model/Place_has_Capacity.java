package com.example.xavi.triaculturadroid.Model;

/**
 * Created by demian ${EMAIL}
 * 02/26/18.
 */

public class Place_has_Capacity {
    private int capacity_id ;
    private int place_id ;
    private int vol_capacity ;
    private double fee ;

    public Capacity capacity ;
    public Place place ;

    public int getCapacity_id() {
        return capacity_id;
    }

    public void setCapacity_id(int capacity_id) {
        this.capacity_id = capacity_id;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public int getVol_capacity() {
        return vol_capacity;
    }

    public void setVol_capacity(int vol_capacity) {
        this.vol_capacity = vol_capacity;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
