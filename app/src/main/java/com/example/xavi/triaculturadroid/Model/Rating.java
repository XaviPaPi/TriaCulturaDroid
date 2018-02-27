package com.example.xavi.triaculturadroid.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by miquel on 2/23/2018.
 */

public class Rating {
    @SerializedName("id_rating")
    @Expose
    private int id;
    @SerializedName("user_id")
    @Expose
    private int user_id;
    @SerializedName("project_id")
    @Expose
    private int proj_id;
    @SerializedName("rate")
    @Expose
    private int rate;
    @SerializedName("comment")
    @Expose
    private String comment;

    private Project project;
    private User user;

    public Rating() {

    }

    public Rating(int id, int user_id, int proj_id, int rate, String comment) {
        this.id = id;
        this.user_id = user_id;
        this.proj_id = proj_id;
        this.rate = rate;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getProj_id() {
        return proj_id;
    }

    public void setProj_id(int proj_id) {
        this.proj_id = proj_id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
