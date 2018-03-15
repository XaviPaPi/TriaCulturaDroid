package com.example.xavi.triaculturadroid.Data.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by miquel on 3/14/2018.
 */

public class userTransfer implements Serializable {
    public int id;
    private String dni;
    private String password;
    private ArrayList<Rating> ratings;
    private ArrayList<Vote> votes;

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public userTransfer() {
        this.ratings = new ArrayList<Rating>();
        this.votes = new ArrayList<Vote>();
    }

    public userTransfer(int id, String dni, String password) {
        this.id = id;
        this.dni = dni;
        this.password = password;
        this.ratings = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }
}
