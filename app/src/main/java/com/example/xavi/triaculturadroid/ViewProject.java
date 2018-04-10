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

import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.Data.PintarEstrelles;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ViewProject extends AppCompatActivity {

    userTransfer user;
    int idProjecte;
    PintarEstrelles pintarEstrelles;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);

        Intent intent = new Intent();

        user = new userTransfer(APIUtils.get_user_by_dni(this.getIntent().getExtras().getString("Usuari")));
        idProjecte = this.getIntent().getExtras().getInt("idProjecte");


        pintarEstrelles=(PintarEstrelles)findViewById(R.id.P_estrelles);
    }

}
