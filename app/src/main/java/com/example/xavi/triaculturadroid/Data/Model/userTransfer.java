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
    private String name;
    private String email;

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

    public userTransfer(int id, String dni, String password, String name, String email) {
        this.id = id;
        this.dni = dni;
        this.password = password;
        this.name = name;
        this.email = email;
        this.ratings = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    public userTransfer(User user) {
        this.id = user.getId();
        this.dni = user.getDni();
        this.password = user.getPassword();
        this.ratings = user.getRatings();
        this.votes = user.getVotes();
        this.name = user.getName();
        this.email = user.getEmail();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
