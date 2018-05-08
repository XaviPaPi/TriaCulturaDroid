package com.example.xavi.triaculturadroid.Data.Model;


public class Historial {
    private int id;
    private String title;
    private String data;
    private double rating;
    private int count_votes;

    public Historial(){
    }

    public Historial(String title, String data, double rating, int id, int count_votes){
        this.id=id;
        this.title = title;
        this.data = data;
        this.rating = rating;
        this.count_votes = count_votes;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCount_votes() {
        return count_votes;
    }

    public void setCount_votes(int count_votes) {
        this.count_votes = count_votes;
    }
}
