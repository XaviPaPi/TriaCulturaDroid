package com.example.xavi.triaculturadroid.Data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;

/**
 * Created by miquel on 2/23/2018.
 */

public class Vote {

    @SerializedName("id_vote")
    @Expose
    private int id;
    @SerializedName("date")
    @Expose
    private String dateVote;
    @SerializedName("project_id")
    @Expose
    private int proj_id;
    @SerializedName("user_id")
    @Expose
    private int user_id;

    public Project project;
    public User user;

    public Vote() {

    }

    public Vote(int id, String dateVote, int proj_id, int user_id) {
        this.id = id;
        this.dateVote = dateVote;
        this.proj_id = proj_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateVote() {
        return dateVote;
    }

    public void setDateVote(String dateVote) {
        this.dateVote = dateVote;
    }

    public int getProj_id() {
        return proj_id;
    }

    public void setProj_id(int proj_id) {
        this.proj_id = proj_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
