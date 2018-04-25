package com.example.xavi.triaculturadroid.Data.Model;

public class Profile_Historial {

    private int id;
    private String title;
    private String data;
    private boolean WinOrLose;


    public Profile_Historial(){
    }

    public Profile_Historial(String title, String data, boolean WinOrLose, int id){
        this.id=id;
        this.title = title;
        this.data = data;
        this.WinOrLose = WinOrLose;
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

    public boolean isWinOrLose() {
        return WinOrLose;
    }

    public void setWinOrLose(boolean winOrLose) {
        WinOrLose = winOrLose;
    }




}
