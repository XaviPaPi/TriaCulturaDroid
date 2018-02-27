package com.example.xavi.triaculturadroid.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashSet;

/**
 * Created by demian ${EMAIL}
 * 02/26/18.
 */

public class User {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("id")
    @Expose
    private String dni;
    @SerializedName("password")
    @Expose
    private String password;

    public HashSet<Rating> ratings;
    public HashSet<Vote> votes;

    public User() {
        this.ratings = new HashSet<Rating>();
        this.votes = new HashSet<Vote>();
    }

    public User(int id, String dni, String password) {
        this.id = id;
        this.dni = dni;
        this.password = password;
        this.ratings = new HashSet<>();
        this.votes = new HashSet<>();
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
