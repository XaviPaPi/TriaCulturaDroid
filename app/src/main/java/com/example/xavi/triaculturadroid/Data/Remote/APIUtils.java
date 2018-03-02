package com.example.xavi.triaculturadroid.Data.Remote;

import android.util.Log;

import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Model.User;

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

    static ArrayList<Project> projectList = new ArrayList<>();
    static User current_user = new User();

    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static ArrayList<Project> get_projects_from_place (int place_id) {

        getApiService().getProjectFromPlace(place_id).subscribeOn(
                Schedulers.io()).subscribe(new Subscriber<List<Project>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Completed.");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(List<Project> projects) {
                projectList.addAll(projects);
            }
        });
        return projectList;
    }

    public static User get_user_by_dni (String dni) {

        getApiService().getUserByDni(dni).subscribeOn(
                Schedulers.io()
        ).subscribe( new Subscriber<User>() {

            @Override
            public void onCompleted() {
                Log.d(TAG, "User recovered.");
            }

            @Override
            public void onError(Throwable e) {
                current_user = null;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(User user) {
                current_user = user;
            }
        });
        return current_user;
    }
}
