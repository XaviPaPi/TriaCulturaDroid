package com.example.xavi.triaculturadroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.Data.PintarEstrelles;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ViewProject extends AppCompatActivity {

    Project p;
    userTransfer user;
    int idProjecte;
    PintarEstrelles pintarEstrelles;
    TextView txt_Author;
    TextView txt_Title;
    TextView txt_Description;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        Intent intent = new Intent();
        //(UserNormal) getIntent().getExtras().getSerializable("User");
        user = (userTransfer) getIntent().getExtras().getSerializable("Usuari");
        idProjecte = (int) getIntent().getExtras().getInt("idProject");
        p = APIUtils.get_Project(idProjecte);

        txt_Author = (TextView)findViewById(R.id.P_AuthorName);
        txt_Title = (TextView)findViewById(R.id.P_Title);
        txt_Description = (TextView)findViewById(R.id.P_Description);

        txt_Author.setText(p.getAuthor().getName().toString());
        txt_Title.setText(p.getTitle().toString());
        txt_Description.setText(p.getDescript().toString());

        pintarEstrelles=(PintarEstrelles)findViewById(R.id.P_estrelles);


        //pintarEstrelles.getEstat();
    }

}
