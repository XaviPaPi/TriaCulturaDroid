package com.example.xavi.triaculturadroid.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashSet;

/**
 * Created by miquel on 2/22/2018.
 */

public class Project {

    @SerializedName("id_project")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String descript;
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("author_id")
    @Expose
    private int author_id;

    public HashSet<File> files;
    public HashSet<Rating> ratings;
    public HashSet<Request> requests;
    public HashSet<Vote> votes;

    public Project() {
        this.files = new HashSet<>();
        this.ratings = new HashSet<>();
        this.requests = new HashSet<>();
        this.votes = new HashSet<>();
    }

    public Project(int id, String title, String descript) {
        this.files = new HashSet<>();
        this.ratings = new HashSet<>();
        this.requests = new HashSet<>();
        this.votes = new HashSet<>();
        this.id = id;
        this.title = title;
        this.descript = descript;
    }

    public Project(int id, String title, String descript, String topic, String type, int author_id) {
        this.id = id;
        this.title = title;
        this.descript = descript;
        this.topic = topic;
        this.type = type;
        this.author_id = author_id;
        this.files = new HashSet<>();
        this.ratings = new HashSet<>();
        this.requests = new HashSet<>();
        this.votes = new HashSet<>();
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

}
