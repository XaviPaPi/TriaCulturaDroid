package com.example.xavi.triaculturadroid.Model;

/**
 * Created by miquel on 2/22/2018.
 */

public class Project {
    private int id;
    private String title;
    private String descript;
    private String topic;
    private String type;
    private int author_id;
    private String name;

    public Project(){

    }

    public Project(int id, String title, String descript, String name) {
        this.id=id;
        this.title = title;
        this.descript = descript;
        this.name = name;
    }

    public Project(int id, String title, String descript, String topic, String type, int author_id) {
        this.id = id;
        this.title = title;
        this.descript = descript;
        this.topic = topic;
        this.type = type;
        this.author_id = author_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
