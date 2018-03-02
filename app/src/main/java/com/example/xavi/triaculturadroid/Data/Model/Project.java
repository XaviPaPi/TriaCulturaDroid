package com.example.xavi.triaculturadroid.Data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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
    @SerializedName("author_dni")
    @Expose
    private String author_dni;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("files")
    @Expose
    private ArrayList<File> files;
    @SerializedName("ratings")
    @Expose
    private ArrayList<Rating> ratings;
    @SerializedName("requests")
    @Expose
    private ArrayList<Request> requests;
    @SerializedName("votes")
    @Expose
    private ArrayList<Vote> votes;

    public Project() {
        this.files = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.requests = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    public Project(String id, String title, String descript) {
        this.files = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.requests = new ArrayList<>();
        this.votes = new ArrayList<>();
        this.author_dni = id;
        this.title = title;
        this.descript = descript;
    }

    public Project(int id, String title, String descript, String topic, String type, String author_id) {
        this.id = id;
        this.title = title;
        this.descript = descript;
        this.topic = topic;
        this.type = type;
        this.author_dni = author_id;
        this.files = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.requests = new ArrayList<>();
        this.votes = new ArrayList<>();
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

    public String getAuthor_dni() {
        return author_dni;
    }

    public void setAuthor_dni(String author_id) {
        this.author_dni = author_id;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }
}
