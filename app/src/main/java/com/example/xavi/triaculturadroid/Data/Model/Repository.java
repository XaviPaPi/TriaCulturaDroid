package com.example.xavi.triaculturadroid.Data.Model;

import java.util.ArrayList;

/**
 * Created by demian ${EMAIL}
 * 02/26/18.
 */

public class Repository {

    private String web_service_api = "https://triaculturaservice.azurewebsites.net/api/";

    public User getCurrentUser(String dni) {
        User u = null;
        return u;
    }

    public User setNewPass (User current_user, String new_password) {
        User u = null;
        return u;
    }

    public ArrayList<Project> getProjectsOnVoting (int place_id) {
        ArrayList<Project> pl = new ArrayList<>();
        /**/
        return pl;
    }

    public ArrayList<Vote> getVotesByUser (int user_id) {
        ArrayList<Vote> vl = new ArrayList<>();
        /**/
        return vl;
    }

    public ArrayList<Request> getWinningProjects () {
        ArrayList<Request> rl = new ArrayList<>();
        /**/
        return rl;
    }

    public Vote insertVote (Vote v) {
        Vote vote_aux = null;
        return vote_aux;
    }

    public void removeVote (int user_id, int project_id) {
        System.out.println("PLACEHOLDER");
    }

    public ArrayList<File> getFilesFromProject (int project_id) {
        ArrayList<File> fl = new ArrayList<>();
        return fl;
    }

    public Author getAuthorFromProject (int project_id) {
        Author a = null;
        return a;
    }

    public double getAverageRatingsForProject (int project_id) {
        return 0.0;
    }

    public Rating getRatingbyUserAndProject (int user_id, int project_id) {
        Rating r = null;
        return r;
    }

}
