package com.example.xavi.triaculturadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;

import org.w3c.dom.Text;

public class ProjectActivityProfile extends AppCompatActivity {

    TextView author;
    TextView title_project;
    TextView descrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_profile);

        author = findViewById(R.id.PH_AuthorName);
        title_project = findViewById(R.id.PH_Title);
        descrip = findViewById(R.id.PH_Description);


        int id = 0;
//        getIntent().getIntExtra("Id" ,id);
        int idpro = getIntent().getIntExtra("Id",0 );
        if (idpro != 0 ) {
            Project pro = APIUtils.get_Project(idpro);
            author.setText(pro.getAuthor().getName() + " " + pro.getAuthor().getSurname());
            title_project.setText(pro.getTitle());
            descrip.setText(pro.getDescript());
        } else {
            descrip.setText("El projecte no s'ha trobat.");
        }

    }
}
