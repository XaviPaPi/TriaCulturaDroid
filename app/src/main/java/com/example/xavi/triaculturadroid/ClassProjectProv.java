package com.example.xavi.triaculturadroid;

/**
 * Created by miquel on 2/22/2018.
 */

public class ClassProjectProv {
    private int id;
    private String title;
    private String descript;
    private String name;

    public ClassProjectProv(int id,String title, String descript, String name) {
        this.id=id;
        this.title = title;
        this.descript = descript;
        this.name = name;
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
