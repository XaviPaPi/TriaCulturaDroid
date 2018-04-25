package com.example.xavi.triaculturadroid.Data.Remote;

import android.app.ProgressDialog;
import android.util.Log;

import com.example.xavi.triaculturadroid.Data.Model.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.PUT;
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
    public static boolean continuar;

    // variables para devolver los valores
    public static List<Project> projectList = new ArrayList<>();
    public static User current_user = new User();
    public static List<Vote> user_votes = new ArrayList<>();
    public static Vote aux_vote = new Vote();
    public static List<Request> winning_requests = new ArrayList<>();
    public static List<File> project_files = new ArrayList<>();
    public static Author project_author = new Author();
    public static Double project_avg = 0.0;
    public static Rating user_rating = new Rating();
    public static Integer current_place_id = 0;
    public static File seeked_file = new File();
    public static Project selected_project = new Project();
    public static Rating user_rate = new Rating();

    public static void init_service() {
        service = RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static APIService getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static List<Project> get_projects_by_place(int place_id) {
        continuar = false;
        getApiService().getProjectByPlace(place_id).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                continuar = true;
                projectList = response.body();
                Log.d(TAG, "Completed.");
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                continuar = true;
                Log.d(TAG, "onError:" + t.toString());
            }
        });
        while (!continuar) {

        }
        return projectList;
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
                continuar = true;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(List<Project> projects) {
                projectList = projects;
            }
        });
        while (!continuar) {
//            Log.d(TAG, "get_projects_from_place: QUERY NOT COMPLETED - WAITING FOR RESPONSE FROM SERVER");
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
                continuar = true;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(User user) {
                Log.d(TAG, "onNext: USER RETRIEVED - " + user.getDni());
                current_user = user;
            }
        });

        while (!continuar) {
            Log.d(TAG, "get_Project: User Not found - WAITING FOR RESPONSE FROM SERVER");
        }

        return current_user;
    }

    public static Project get_Project(int id) {
        continuar = false;
        service.getProjectById(id).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Project>() {
            @Override
            public void onCompleted() {
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                continuar = true;
            }

            @Override
            public void onNext(Project project) {
                selected_project = project;
            }
        });
        while (!continuar) {
            Log.d(TAG, "get_Project: QUERY NOT COMPLETED - WAITING FOR RESPONSE FROM SERVER");
        }
        return selected_project;
    }

    public static User update_user(User u) {
        continuar = false;
        service.postNewPass(u.getId(), u.getPassword(), u.getEmail(), u.getName()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
                continuar = true;
                Log.d(TAG, "User modified.");
            }

            @Override
            public void onError(Throwable e) {
                current_user = null;
                continuar = true;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(User user) {
                Log.d(TAG, "onNext: USER RETRIEVED - " + user.getDni());
                current_user = user;
            }
        });
        while (!continuar) {
            Log.d(TAG, "update_user: QUERY NOT COMPLETED - WAITING FOR RESPONSE FROM SERVER");
        }
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
                continuar = true;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(List<Vote> votes) {
                user_votes = votes;
            }
        });
        while (!continuar) {
            Log.d(TAG, "get_user_by_dni: QUERY NOT COMPLETED - WAITING FOR RESPONSE FROM SERVER");
        }
        return user_votes;
    }

    public static Vote post_new_vote(Vote vote) {
        continuar = false;
        aux_vote = null;
        service.postNewVote(vote.getUser_id(), vote.getProj_id()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Vote>() {
            @Override
            public void onCompleted() {
                continuar = true;
                Log.d(TAG, "Vote posted.");
            }

            @Override
            public void onError(Throwable e) {
                continuar = true;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(Vote vote) {
                aux_vote = vote;
            }
        });
        while (!continuar) {
        }
        return aux_vote;
    }

    public static Vote get_vote(int user_id, int project_id) {
        continuar = false;
        service.getSingleVote(user_id, project_id).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Vote>() {
            @Override
            public void onCompleted() {
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                continuar = true;
            }

            @Override
            public void onNext(Vote vote) {
                aux_vote = vote;
            }
        });
        while (!continuar) {
        }
        return aux_vote;
    }

    public static void delete_vote(Vote vote) {

        continuar = false;
        service.deleteVote(vote.getId()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<Vote>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Vote deleted.");
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                continuar = true;
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
        service.getWinningRequests().subscribeOn(Schedulers.io()).subscribe(new Subscriber<List<Request>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Requests retrieved.");
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                continuar = true;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(List<Request> requests) {
                winning_requests = requests;
            }
        });
        while (!continuar) {
            Log.d(TAG, "get_files_from_project: WAITING...");
        }
        return winning_requests;
    }

    public static List<File> get_files_from_project(Project p) {
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
                continuar = true;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(List<File> files) {
                project_files = files;
            }
        });
        while (!continuar) {
            Log.d(TAG, "get_files_from_project: WAITING...");
        }
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
                continuar = true;
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
                continuar = true;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(Double aDouble) {
                project_avg = aDouble;
            }
        });
        while (!continuar) {
            Log.d(TAG, "get_project_avg: WAITING...");
        }
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
                continuar = true;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(Rating rating) {
                user_rating = rating;
            }
        });
        while (!continuar) {
            Log.d(TAG, "get_rating_where_user: WAITING...");
        }
        return user_rating;
    }

    public static Integer get_current_place() {
        continuar = false;
        service.getCurrentPlace().subscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                continuar = true;
            }

            @Override
            public void onNext(Integer integer) {
                current_place_id = integer;
            }
        });
        while (!continuar) {
        }
        return current_place_id;
    }

    public static File get_file_by_id(int id) {
        continuar = false;
        service.getFileById(id).subscribeOn(Schedulers.io()).subscribe(new Subscriber<File>() {
            @Override
            public void onCompleted() {
                continuar = true;
            }

            @Override
            public void onError(Throwable e) {
                continuar = true;
//                Log.d(TAG, "onError: "+e.toString());
                e.printStackTrace();
            }

            @Override
            public void onNext(File file) {
                seeked_file = file;
            }
        });
        while (!continuar) {

        }
        return seeked_file;
    }

    public static Rating update_rate(Rating r) {
        continuar = false;
        service.postNewRating(r.getId(), r.getUser_id(), r.getProj_id(), r.getRate()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<PUT>() {
            @Override
            public void onCompleted() {
                continuar = true;
                Log.d(TAG, "Rate modifie.");
            }

            @Override
            public void onError(Throwable e) {
                user_rate = null;
                continuar = true;
                Log.d(TAG, "onError:" + e.toString());
            }

            @Override
            public void onNext(PUT put) {
                Log.d(TAG, "onNext: " + put.toString());
            }
        });
        while (!continuar) {
        }
        return r;
    }


}
