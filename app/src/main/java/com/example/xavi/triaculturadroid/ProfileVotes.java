package com.example.xavi.triaculturadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xavi.triaculturadroid.Adapters.AdapterHistoProfile;
import com.example.xavi.triaculturadroid.Adapters.AdapterHistorial;
import com.example.xavi.triaculturadroid.Data.Model.Historial;
import com.example.xavi.triaculturadroid.Data.Model.Profile_Historial;
import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Model.Request;
import com.example.xavi.triaculturadroid.Data.Model.Vote;
import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

public class ProfileVotes extends AppCompatActivity {

    int id_user;
    List<Profile_Historial> historial_Projects_List = new ArrayList<Profile_Historial>();
    Profile_Historial historial;
    userTransfer user;
    String data;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_votes);

        ListView list_historial = (ListView) findViewById(R.id.ListHistorialProjects);
        id_user = getIntent().getExtras().getInt("Usuari");
        omplirLlistaJocs();

        AdapterHistoProfile adapter = new AdapterHistoProfile(ProfileVotes.this, historial_Projects_List);
        list_historial.setItemsCanFocus(true);
        list_historial.setAdapter(adapter);

        list_historial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int pro = historial_Projects_List.get(position).getId();
                Intent project_intent = new Intent(getApplication(), ProjectActivityProfile.class);
                project_intent.putExtra("Id", pro);
                startActivity(project_intent);
            }
        });
    }


    private void omplirLlistaJocs() {

        List<Vote> historial_List = APIUtils.get_votes(id_user);
        historial_Projects_List = new ArrayList<>();

        for (Vote r : historial_List) {
            historial = new Profile_Historial();
            historial.setId(r.getProj_id());
//            historial.setData(r.getDateVote());
            //historial.setTitle(r.getProject().getTitle());
            historial_Projects_List.add(historial);
        }


    }
}

