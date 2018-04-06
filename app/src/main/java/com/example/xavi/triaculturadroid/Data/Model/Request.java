package com.example.xavi.triaculturadroid.Data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by miquel on 2/23/2018.
 */

public class Request {

    @SerializedName("id_request")
    @Expose
    private int id;
    @SerializedName("proposed_date")
    @Expose
    private String data_proposta;
    @SerializedName("is_winner")
    @Expose
    private int esGuanyador;
    @SerializedName("project_id")
    @Expose
    private int proj_id;
    @SerializedName("place_id")
    @Expose
    private int place_id;

    private Place place;

    private Project project;

    public Request() {
    }

    public Request(int id, String data_proposta, int esGuanyador, int proj_id, int place_id) {
        this.id = id;
        this.data_proposta = data_proposta;
        this.esGuanyador = esGuanyador;
        this.proj_id = proj_id;
        this.place_id = place_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData_proposta() {
        return data_proposta;
    }

    public void setData_proposta(String data_proposta) {
        this.data_proposta = data_proposta;
    }

    public int isEsGuanyador() {
        return esGuanyador;
    }

    public void setEsGuanyador(int esGuanyador) {
        this.esGuanyador = esGuanyador;
    }

    public int getProj_id() {
        return proj_id;
    }

    public void setProj_id(int proj_id) {
        this.proj_id = proj_id;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
