package com.example.xavi.triaculturadroid.Data.Remote;

import android.util.Log;

import com.example.xavi.triaculturadroid.Data.Model.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by demian ${EMAIL}
 * 03/01/18.
 */

public class APIUtils {
    private static final String BASE_URL = "https://triaculturaservice.azurewebsites.net/";
    static APIService service;

    // por si hay que detenerlo internamente
    static boolean continuar;

    // variables para devolver los valores
    static List<Project> projectList = new ArrayList<>();
    static User current_user = new User();
    static List<Vote> user_votes = new ArrayList<>();
    static Vote aux_vote = new Vote();
    static List<Request> winning_requests = new ArrayList<>();
    static List<File> project_files = new ArrayList<>();
    static Author project_author = new Author();
    static Double project_avg = 0.0;
    static Rating user_rating = new Rating();

    public static void init_service() {
        service = RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static APIService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static List<Project> get_projects_from_place(int place_id) {
        continuar = false;
        service.getProjectFromPlace(place_id).subscribeOn(
                Schedulers.io()).subscribe(new Subscriber<List<Project>>() {
            @Override
            public void onCompleted() {
                continuar = true;
                Log.d(TAG, "Completed.");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(List<Project> projects) {
                projectList = projects;
            }
        });
        while (!continuar) {
            Log.d(TAG, "get_projects_from_place: QUERY NOT COMPLETED - WAITING FOR RESPONSE FROM SERVER");
        }

        return projectList;
    }

    public static User get_user_by_dni(String dni) {
        continuar = false;

        service.getUserByDni(dni).subscribeOn(
                Schedulers.io()
        ).subscribe(new Subscriber<User>() {

            @Override
            public void onCompleted() {
                continuar = true;
                Log.d(TAG, "User recovered.");
            }

            @Override
            public void onError(Throwable e) {
                current_user = null;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(User user) {
                Log.d(TAG, "onNext: USER RETRIEVED - " + user.getDni());
                current_user = user;
            }
        });
        while (!continuar) {
            Log.d(TAG, "get_user_by_dni: QUERY NOT COMPLETED - WAITING FOR RESPONSE FROM SERVER");
        }
        return current_user;
    }

    public static User update_user(int id, String password) {
        continuar = false;
        service.postNewPass(id, password).subscribeOn(Schedulers.io()).subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
                continuar = true;
                Log.d(TAG, "User modified.");
            }

            @Override
            public void onError(Throwable e) {
                current_user = null;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(User user) {
                Log.d(TAG, "onNext: USER RETRIEVED - " + user.getDni());
                current_user = user;
            }
        });
        return current_user;
    }

    public static List<Vote> get_votes(int user_id) {
        continuar = false;
        service.getVotes(user_id).subscribeOn(Schedulers.io()).subscribe(new Subscriber<List<Vote>>() {
            @Override
            public void onCompleted() {
                continuar = true;
                Log.d(TAG, "Votes retrieved.");
            }

            @Override
            public void onError(Throwable e) {
                user_votes = new ArrayList<>();
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(List<Vote> votes) {
                user_votes = votes;
            }
        });
        return user_votes;
    }

    public static Vote post_new_vote(int user_id, int project_id) {
        continuar = false;
        aux_vote = null;
        service.postNewVote(user_id, project_id).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Vote>() {
            @Override
            public void onCompleted() {
                continuar = true;
                Log.d(TAG, "Vote posted.");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(Vote vote) {
                aux_vote = vote;
            }
        });
        return aux_vote;
    }

    public static void delete_vote(int user_id, int project_id) {
        continuar = false;
        service.postNewVote(user_id, project_id).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Vote>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Vote deleted.");
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(Vote vote) {

            }
        });
    }

    public static List<Request> get_winning_requests() {
        continuar = false;
        winning_requests = new ArrayList<>();
        service.getWinningRequests().subscribeOn(Schedulers.io()).subscribe(new Subscriber<java.util.List<com.example.xavi.triaculturadroid.Data.Model.Request>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Requests retrieved.");
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(List<com.example.xavi.triaculturadroid.Data.Model.Request> requests) {
                winning_requests = requests;
            }
        });
        return winning_requests;
    }

    public List<File> get_files_from_project(Project p) {
        continuar = false;
        project_files = new ArrayList<>();
        service.getFilesFromProject(p.getId()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<List<File>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Files retrieved.");
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(List<File> files) {
                project_files = files;
            }
        });
        return project_files;
    }

    public static Author get_author_from_project(Project p) {
        continuar = false;
        project_author = null;
        service.getAuthorFromProject(p.getId()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Author>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Author retrieved.");
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(Author author) {
                project_author = author;
            }
        });
        return project_author;
    }

    public static Double get_project_avg(Project p) {
        continuar = false;
        project_avg = 0.0;
        service.getProjectAvgRating(p.getId()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Double>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Average retrieved.");
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(Double aDouble) {
                project_avg = aDouble;
            }
        });
        return project_avg;
    }

    public static Rating get_rating_where_user(int user_id, int project_id) {
        continuar = false;
        user_rating = null;
        service.getRatingWhereUser(user_id, project_id).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Rating>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Rating retrieved.");
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(Rating rating) {
                user_rating = rating;
            }
        });
        return user_rating;
    }


}
