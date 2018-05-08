package com.example.xavi.triaculturadroid.Data.Remote;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @PUT("api/usuari/")
    @FormUrlEncoded
    Observable<User> postNewPass(
            @Field("id") int id,
            @Field("password") String password
            , @Field("email") String email,
            @Field("name") String name
    );

    @PUT("api/rating/")
    @FormUrlEncoded
    Observable<PUT> postNewRating(
            @Field("id_rating") int id_rating,
            @Field("user_id") int user_id,
            @Field("project_id") int project_id,
            @Field("rate") int rate
            //@Field("comment") String comment
    );

    @GET("api/usuari/{dni}")
//    @FormUrlEncoded
    Observable<User> getUserByDni(@Path("dni") String dni);

    @GET("api/projects/{place_id}")
//    @FormUrlEncoded
    Call<List<Project>> getProjectByPlace(@Path("place_id") int place_id);

    @GET("api/projects/{place_id}")
//    @FormUrlEncoded
    Observable<List<Project>> getProjectFromPlace(@Path("place_id") int place_id);

    @GET("api/votes/{user_id}")
//    @FormUrlEncoded
    Observable<List<Vote>> getVotes(@Path("user_id") int user_id);

    @POST("api/votes/{user_id}/{project_id}")
//    @FormUrlEncoded
    Observable<Vote> postNewVote(@Path("user_id") int user_id,
                                 @Path("project_id") int project_id);

    @GET("api/vote/{user_id}/{project_id}")
    Observable<Vote> getSingleVote(@Path("user_id") int user_id,
                                   @Path("project_id") int project_id);

    @DELETE("api/votes/{vote_id}")
    Observable<Vote> deleteVote(@Path("vote_id") int vote_id);

    @GET("api/winningrequests")
//    @FormUrlEncoded
    Observable<List<Request>> getWinningRequests(); //NOT SURE

    @GET("api/files/{project_id}")
//    @FormUrlEncoded
    Observable<List<File>> getFilesFromProject(@Path("project_id") int project_id);

    @GET("api/file/{id_file}")
//    @FormUrlEncoded
    Observable<File> getFilesFromId(@Path("id_file") int id_file);

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

    @GET("api/current")
    Observable<Integer> getCurrentPlace();

    @GET("api/file/{file_id}")
    Observable<File> getFileById(@Path("file_id") int file_id);

    @GET("api/project/{project_id}")
    Observable<Project> getProjectById(@Path("project_id") int project_id);

    @GET("api/rating/last_id")
    Observable<Integer> getLastId();

    @GET("api/requests/{user_id}")
    Observable<List<Request>> getVotedRequests(@Path("user_id") int user_id);

    @GET("api/project/votes/{proj_id}")
    Observable<Integer> getCountVotes(@Path("proj_id") int project_id);
}

