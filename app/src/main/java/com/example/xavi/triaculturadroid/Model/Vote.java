package com.example.xavi.triaculturadroid.Model;

import java.text.DateFormat;

/**
 * Created by miquel on 2/23/2018.
 */

public class Vote {
    private int id;
    private DateFormat dateFormat;
    private int proj_id;
    private int user_id;

    public Vote(int id, DateFormat dateFormat, int proj_id, int user_id) {
        this.id = id;
        this.dateFormat = dateFormat;
        this.proj_id = proj_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
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
}
