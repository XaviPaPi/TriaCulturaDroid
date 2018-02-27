package com.example.xavi.triaculturadroid.Data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by miquel on 2/23/2018.
 */

public class File {

    @SerializedName("id_file")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("extension")
    @Expose
    private String extension;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("project_id")
    @Expose
    private int proj_id;

    public Project project;

    public File() {
    }

    public File(int id, String name, String extension, String path, int proj_id) {
        this.id = id;
        this.name = name;
        this.extension = extension;
        this.path = path;
        this.proj_id = proj_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getProj_id() {
        return proj_id;
    }

    public void setProj_id(int proj_id) {
        this.proj_id = proj_id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
