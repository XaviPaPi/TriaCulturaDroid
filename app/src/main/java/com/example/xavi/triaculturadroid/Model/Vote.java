package com.example.xavi.triaculturadroid.Model;

import java.text.DateFormat;

/**
 * Created by miquel on 2/23/2018.
 */

public class Vote {
    private int id;
    private DateFormat dateVote;
    private int proj_id;
    private int user_id;
    public Project project;
    public User user;

    public Vote() {

    }

    public Vote(int id, DateFormat dateVote, int proj_id, int user_id) {
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

    public DateFormat getDateVote() {
        return dateVote;
    }

    public void setDateVote(DateFormat dateVote) {
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
