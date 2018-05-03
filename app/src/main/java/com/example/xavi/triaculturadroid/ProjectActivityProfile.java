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
        //TODO mirar esto que no se sabe como va
        Project pro = APIUtils.get_Project(getIntent().getIntExtra("Id" ,id));


    }
}
