package com.example.xavi.triaculturadroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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

import com.example.xavi.triaculturadroid.Data.PintarEstrelles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ViewProject extends AppCompatActivity {// implements View.OnClickListener {
    private static final String TAG = "";
    //ImageButton image_P_star1, image_P_star2, image_P_star3, image_P_star4, image_P_star5;
    int vot = 0;
    boolean isPressed = false;
    PintarEstrelles pintarEstrelles;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);
      /*  image_P_star1=(ImageButton)findViewById(R.id.P_star1);
        image_P_star2=(ImageButton)findViewById(R.id.P_star2);
        image_P_star3=(ImageButton)findViewById(R.id.P_star3);
        image_P_star4=(ImageButton)findViewById(R.id.P_star4);
        image_P_star5=(ImageButton)findViewById(R.id.P_star5);



        image_P_star1.setOnClickListener(this);
        image_P_star2.setOnClickListener(this);
        image_P_star3.setOnClickListener(this);
        image_P_star4.setOnClickListener(this);
        image_P_star5.setOnClickListener(this);
*/

        pintarEstrelles=(PintarEstrelles)findViewById(R.id.P_estrelles);
    }
//region commented
 /*   public void onClick(View v) {
        switch (v.getId()) {
            case R.id.P_star1:
                if (vot!=1) {
                    vot = 1;
                    image_P_star1.setImageResource(R.drawable.star_activated);
                    image_P_star2.setImageResource(R.drawable.star);
                    image_P_star3.setImageResource(R.drawable.star);
                    image_P_star4.setImageResource(R.drawable.star);
                    image_P_star5.setImageResource(R.drawable.star);
                }else{
                    vot=0;
                    image_P_star1.setImageResource(R.drawable.star);
                    image_P_star2.setImageResource(R.drawable.star);
                    image_P_star3.setImageResource(R.drawable.star);
                    image_P_star4.setImageResource(R.drawable.star);
                    image_P_star5.setImageResource(R.drawable.star);
                }
                break;
            case R.id.P_star2:
                if (vot!=2){
                    vot=2;
                    image_P_star1.setImageResource(R.drawable.star_activated);
                    image_P_star2.setImageResource(R.drawable.star_activated);
                    image_P_star3.setImageResource(R.drawable.star);
                    image_P_star4.setImageResource(R.drawable.star);
                    image_P_star5.setImageResource(R.drawable.star);
                }else {
                    vot=0;
                    image_P_star1.setImageResource(R.drawable.star);
                    image_P_star2.setImageResource(R.drawable.star);
                }
                break;
            case R.id.P_star3:
                if (vot!=3) {
                    vot = 3;
                    image_P_star1.setImageResource(R.drawable.star_activated);
                    image_P_star2.setImageResource(R.drawable.star_activated);
                    image_P_star3.setImageResource(R.drawable.star_activated);
                    image_P_star4.setImageResource(R.drawable.star);
                    image_P_star5.setImageResource(R.drawable.star);
                }else{
                    vot=0;
                    image_P_star1.setImageResource(R.drawable.star);
                    image_P_star2.setImageResource(R.drawable.star);
                    image_P_star3.setImageResource(R.drawable.star);
                }
                break;
            case R.id.P_star4:
                if (vot!=4) {
                    vot = 4;
                    image_P_star1.setImageResource(R.drawable.star_activated);
                    image_P_star2.setImageResource(R.drawable.star_activated);
                    image_P_star3.setImageResource(R.drawable.star_activated);
                    image_P_star4.setImageResource(R.drawable.star_activated);
                    image_P_star5.setImageResource(R.drawable.star);
                }else{
                    vot=0;
                    image_P_star1.setImageResource(R.drawable.star);
                    image_P_star2.setImageResource(R.drawable.star);
                    image_P_star3.setImageResource(R.drawable.star);
                    image_P_star4.setImageResource(R.drawable.star);
                }
                break;
            case R.id.P_star5:
                if (vot!=5) {
                    vot = 5;
                    image_P_star1.setImageResource(R.drawable.star_activated);
                    image_P_star2.setImageResource(R.drawable.star_activated);
                    image_P_star3.setImageResource(R.drawable.star_activated);
                    image_P_star4.setImageResource(R.drawable.star_activated);
                    image_P_star5.setImageResource(R.drawable.star_activated);
                }else{
                    vot=0;
                    image_P_star1.setImageResource(R.drawable.star);
                    image_P_star2.setImageResource(R.drawable.star);
                    image_P_star3.setImageResource(R.drawable.star);
                    image_P_star4.setImageResource(R.drawable.star);
                    image_P_star5.setImageResource(R.drawable.star);
                }
                break;
        }
        isPressed=!isPressed;
    }*/

//endregion


}
