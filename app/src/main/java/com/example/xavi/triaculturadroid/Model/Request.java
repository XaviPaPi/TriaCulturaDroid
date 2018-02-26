package com.example.xavi.triaculturadroid.Model;

import java.text.DateFormat;

/**
 * Created by miquel on 2/23/2018.
 */

public class Request {
    private int id;
    private DateFormat data_proposta;
    private boolean esGuanyador;
    private int proj_id;
    private int place_id;
    private Place place;

    public Request() {
    }

    public Request(int id, DateFormat data_proposta, boolean esGuanyador, int proj_id, int place_id) {
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

    public DateFormat getData_proposta() {
        return data_proposta;
    }

    public void setData_proposta(DateFormat data_proposta) {
        this.data_proposta = data_proposta;
    }

    public boolean isEsGuanyador() {
        return esGuanyador;
    }

    public void setEsGuanyador(boolean esGuanyador) {
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
}
