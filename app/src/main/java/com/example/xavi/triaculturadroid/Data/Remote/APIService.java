package com.example.xavi.triaculturadroid.Data.Remote;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by demian ${EMAIL}
 * 02/27/18.
 */
import com.example.xavi.triaculturadroid.Data.Model.*;

import java.util.HashSet;
import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface APIService {

    @PUT("api/usuari/{id}/{newpass}")
    @FormUrlEncoded
    Observable<User> postNewPass(@Path("id") int id,
                           @Path("newpass") String password);

    @GET("api/usuari/{dni}")
//    @FormUrlEncoded
    Observable<User> getUserByDni(@Path("dni") String dni);

    @GET("api/projects/{place_id}")
//    @FormUrlEncoded
    Observable<List<Project>> getProjectFromPlace(@Path("place_id") int place_id);

    @GET("api/votes/{user_id}")
//    @FormUrlEncoded
    Observable<List<Vote>> getVotes(@Path("user_id") int user_id);

    @PUT("api/votes/{user_id}/{project_id}")
    @FormUrlEncoded
    Observable<Vote> postNewVote(@Path("user_id") int user_id,
                           @Path("project_id") int project_id);

    @DELETE("api/votes{user_id}/{project_id}")
    @FormUrlEncoded
    Observable<Vote> deleteVote(@Path("user_id") int user_id,
                          @Path("project_id") int project_id);

    @GET("api/winningrequests")
//    @FormUrlEncoded
    Observable<List<Request>> getWinningRequests (); //NOT SURE

    @GET("api/files/{project_id}")
//    @FormUrlEncoded
    Observable<List<File>> getFilesFromProject(@Path("project_id") int project_id);

    @GET("api/author/{project_id}")
//    @FormUrlEncoded
    Observable<Author> getAuthorFromProject(@Path("project_id") int project_id);

    @GET("api/rating/{project_id}")
//    @FormUrlEncoded
    Observable<Double> getProjectAvgRating(@Path("project_id") int project_id);

    @GET("api/rating/{user_id}/{project_id}")
//    @FormUrlEncoded
    Observable<Rating> getRatingWhereUser(@Path("user_id") int user_id,
                                      @Path("project_id") int project_id);



}

