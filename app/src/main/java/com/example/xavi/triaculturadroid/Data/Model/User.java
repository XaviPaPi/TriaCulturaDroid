package com.example.xavi.triaculturadroid.Data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by demian ${EMAIL}
 * 02/26/18.
 */

public class User {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("dni")
    @Expose
    private String dni;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("ratings")
    @Expose
    private ArrayList<Rating> ratings;
    @SerializedName("votes")
    @Expose
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

    public User() {
        this.ratings = new ArrayList<Rating>();
        this.votes = new ArrayList<Vote>();
    }

    public User(int id, String dni, String password) {
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
