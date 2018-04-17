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
import android.widget.Toast;

import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Model.Rating;
import com.example.xavi.triaculturadroid.Data.Model.User;
import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.Data.PintarEstrelles;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ViewProject extends AppCompatActivity {
    public static int estat;
    Project p;
    Rating ratingAnterior;
    userTransfer user;
    int idProjecte;
    TextView txt_Author;
    TextView txt_Title;
    TextView txt_Description;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        Intent intent = new Intent();
        //(UserNormal) getIntent().getExtras().getSerializable("User");
        user = new userTransfer(APIUtils.get_user_by_dni(this.getIntent().getExtras().getString("Usuari")));


        idProjecte = (int) getIntent().getExtras().getInt("idProject");
        p = APIUtils.get_Project(idProjecte);


        ratingAnterior = APIUtils.get_rating_where_user(user.getId(), idProjecte);

        if (ratingAnterior != null) {
            estat = ratingAnterior.getRate();
        }
        txt_Author = (TextView) findViewById(R.id.P_AuthorName);
        txt_Title = (TextView) findViewById(R.id.P_Title);
        txt_Description = (TextView) findViewById(R.id.P_Description);

        txt_Author.setText(p.getAuthor().getName().toString());
        txt_Title.setText(p.getTitle().toString());
        txt_Description.setText(p.getDescript().toString());

    }
//ViewProject.estat=APIUtils.get_rating_where_user(user.getId(),idProjecte).getRate();
    //38884596J
    @Override
    protected void onPause() {
        super.onPause();
        int count = 0;
        for (int i = 0; i < user.getRatings().size(); i++) {
            if (user.getRatings().get(i).getProj_id() == idProjecte && user.getRatings().get(count).getId() != 0)
                break;
            count++;
        }
        Rating rating = new Rating();
        if (user.getRatings().size() > count) {
            rating.setId(user.getRatings().get(count).getId());
        }
        rating.setProj_id(idProjecte);
        rating.setRate(estat);
        rating.setUser_id(user.getId());
        APIUtils.update_rate(rating);
        // Toast.makeText(this,""+estat,Toast.LENGTH_SHORT).show();
    }

}