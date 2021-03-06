package com.example.xavi.triaculturadroid.Data;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Toast;

import com.example.xavi.triaculturadroid.R;
import com.example.xavi.triaculturadroid.ViewProject;


/**
 * Created by Computer on 06/04/2018.
 */

public class PintarEstrelles extends View {
    //region varOnTouch..
    private float x = -1;
    private float y = -1;
    private float initialX;
    private float initialY;
    private float offsetX;
    private float offsetY;
    private boolean primer =true;
    private boolean segon = true;
    private boolean tercer =true;
    private boolean quart = true;
    private boolean cinque =true;
    private int estatPosterior=0;
    private boolean tocat = false;
    private boolean primerCop=true;
    private float HeightReal;
    private float WidthtReal;
    float scaledSizeInPixels;

    //endregion


    //region
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        HeightReal = MeasureSpec.getSize(heightMeasureSpec);
        WidthtReal = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        calculTamanyEstrella();

    }
    //endregion

    //region Calcular tamany text
    private void calculTamanyEstrella() {
        float result = HeightReal;
        scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,result, getResources().getDisplayMetrics());
    }


    //endregion

    public PintarEstrelles(Context context) {
        super(context);
        estatPosterior=-1;

    }
    public PintarEstrelles(Context context, AttributeSet attrs) {
        super(context, null);
        estatPosterior=-1;
    }


    Drawable estrella1,estrella2,estrella3,estrella4,estrella5;
    @Override
    protected void onDraw(Canvas canvas) {
        int estrella = (int) ((WidthtReal/2)/5);
        float llargada = WidthtReal/2-(estrella*5)/2;

        if (tocat) {
            if (primer) {
                estrella1 = this.getResources().getDrawable(R.drawable.star_grey);
                estrella1.setBounds((int) llargada, 0, (int)llargada+estrella, (int) HeightReal);
                estrella1.draw(canvas);
            } else {
                estrella1 = this.getResources().getDrawable(R.drawable.star_grey_hover_yelow);
                estrella1.setBounds((int) llargada, 0, (int)llargada+estrella, (int) HeightReal);
                estrella1.draw(canvas);
            }
            if (segon) {
                estrella2 = this.getResources().getDrawable(R.drawable.star_grey);
                estrella2.setBounds((int)llargada+estrella, 0, (int)llargada+estrella*2,(int) HeightReal);
                estrella2.draw(canvas);
            } else {
                estrella2 = this.getResources().getDrawable(R.drawable.star_grey_hover_yelow);
                estrella2.setBounds((int)llargada+estrella, 0, (int)llargada+estrella*2, (int) HeightReal);
                estrella2.draw(canvas);
            }
            if (tercer) {
                estrella3 = this.getResources().getDrawable(R.drawable.star_grey);
                estrella3.setBounds((int)llargada+estrella*2, 0, (int)llargada+estrella*3, (int) HeightReal);
                estrella3.draw(canvas);
            } else {
                estrella3 = this.getResources().getDrawable(R.drawable.star_grey_hover_yelow);
                estrella3.setBounds((int)llargada+estrella*2, 0, (int)llargada+estrella*3, (int) HeightReal);
                estrella3.draw(canvas);
            }
            if (quart) {
                estrella4 = this.getResources().getDrawable(R.drawable.star_grey);
                estrella4.setBounds((int)llargada+estrella*3, 0, (int)llargada+estrella*4, (int) HeightReal);
                estrella4.draw(canvas);
            } else {
                estrella4 = this.getResources().getDrawable(R.drawable.star_grey_hover_yelow);
                estrella4.setBounds((int)llargada+estrella*3, 0, (int)llargada+estrella*4, (int) HeightReal);
                estrella4.draw(canvas);
            }
            if (cinque) {
                estrella5 = this.getResources().getDrawable(R.drawable.star_grey);
                estrella5.setBounds((int)llargada+estrella*4, 0, (int)llargada+estrella*5, (int) HeightReal);
                estrella5.draw(canvas);
            } else {
                estrella5 = this.getResources().getDrawable(R.drawable.star_grey_hover_yelow);
                estrella5.setBounds((int)llargada+estrella*4, 0, (int)llargada+estrella*5, (int) HeightReal);
                estrella5.draw(canvas);
            }
            x = -1;
            y = -1;
        }else{
            if (estatPosterior!=ViewProject.estat&&ViewProject.estat>0) {
                estrella1 = this.getResources().getDrawable(R.drawable.star_yelow);
                estrella1.setBounds((int)llargada, 0, (int)llargada+estrella, (int) HeightReal);
                estrella1.draw(canvas);
            } else {
                estrella1 = this.getResources().getDrawable(R.drawable.star_grey);
                estrella1.setBounds((int)llargada, 0, (int)llargada+estrella, (int) HeightReal);
                estrella1.draw(canvas);
            }
           if (estatPosterior!=ViewProject.estat&&ViewProject.estat>1) {
                estrella2 = this.getResources().getDrawable(R.drawable.star_yelow);
               estrella2.setBounds((int)llargada+estrella, 0, (int)llargada+estrella*2, (int) HeightReal);
               estrella2.draw(canvas);
            } else {
                estrella2 = this.getResources().getDrawable(R.drawable.star_grey);
               estrella2.setBounds((int)llargada+estrella, 0, (int)llargada+estrella*2, (int) HeightReal);
               estrella2.draw(canvas);
            }
            if (estatPosterior!=ViewProject.estat&&ViewProject.estat>2) {
                estrella3 = this.getResources().getDrawable(R.drawable.star_yelow);
                estrella3.setBounds((int)llargada+estrella*2, 0, (int)llargada+estrella*3, (int) HeightReal);
                estrella3.draw(canvas);
            } else {
                estrella3 = this.getResources().getDrawable(R.drawable.star_grey);
                estrella3.setBounds((int)llargada+estrella*2, 0, (int)llargada+estrella*3,(int) HeightReal);
                estrella3.draw(canvas);
            }
            if (estatPosterior!=ViewProject.estat&&ViewProject.estat>3) {
                estrella4 = this.getResources().getDrawable(R.drawable.star_yelow);
                estrella4.setBounds((int)llargada+estrella*3, 0, (int)llargada+estrella*4, (int) HeightReal);
                estrella4.draw(canvas);
            } else {
                estrella4 = this.getResources().getDrawable(R.drawable.star_grey);
                estrella4.setBounds((int)llargada+estrella*3, 0, (int)llargada+estrella*4,(int) HeightReal);
                estrella4.draw(canvas);
            }
            if (estatPosterior!=ViewProject.estat&&ViewProject.estat>4) {
                estrella5 = this.getResources().getDrawable(R.drawable.star_yelow);
                estrella5.setBounds((int)llargada+estrella*4, 0, (int)llargada+estrella*5, (int) HeightReal);
                estrella5.draw(canvas);
            } else {
                estrella5 = this.getResources().getDrawable(R.drawable.star_grey);
                estrella5.setBounds((int)llargada+estrella*4, 0, (int)llargada+estrella*5,(int) HeightReal);
                estrella5.draw(canvas);
            }

            if(!primerCop) {
                tocat=true;
            }else{
                primerCop=false;
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int estrella = (this.getWidth()/2)/5;
        float llargada = this.getWidth()/2-(estrella*5)/2;
        int maskedAction = event.getAction() & MotionEvent.ACTION_MASK;
        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
                initialX = x;
                initialY = y;
                offsetX = event.getX();
                offsetY = event.getY();

                x = initialX + event.getX() - offsetX;
                y = initialY + event.getY() - offsetY;
                if ((offsetX > (int)llargada && offsetX <= (int)llargada+estrella && !primer)||(offsetX >llargada+estrella && offsetX <= llargada+estrella*2 && !segon)
                        ||(offsetX >llargada+estrella*2 && offsetX <= llargada+estrella*3 && !tercer)||(offsetX >llargada+estrella*3 && offsetX <= llargada+estrella*4 && !quart)
                        ||(offsetX >llargada+estrella*4 && offsetX <= llargada+estrella*5 && !cinque)) {
                    if  (offsetX > llargada && offsetX <= llargada+estrella && !primer) {
                        if (segon) {
                            primer = true;
                            segon = true;
                            tercer = true;
                            quart = true;
                            cinque = true;
                            ViewProject.estat = 0;
                        }else{
                            primer = false;
                            segon = true;
                            tercer = true;
                            quart = true;
                            cinque = true;
                            ViewProject.estat=1;
                        }
                    }else if(offsetX >llargada+estrella && offsetX <= llargada+estrella*2 && !segon){
                        if (tercer) {
                            primer = true;
                            segon = true;
                            tercer = true;
                            quart = true;
                            cinque = true;
                            ViewProject.estat = 0;
                        }else{
                            primer = false;
                            segon = false;
                            tercer = true;
                            quart = true;
                            cinque = true;
                            ViewProject.estat=2;
                        }
                    }else if(offsetX >llargada+estrella*2 && offsetX <= llargada+estrella*3 && !tercer){
                        if (quart) {
                            primer = true;
                            segon = true;
                            tercer = true;
                            quart = true;
                            cinque = true;
                            ViewProject.estat = 0;
                        }else{
                            primer = false;
                            segon = false;
                            tercer = false;
                            quart = true;
                            cinque = true;
                            ViewProject.estat=3;
                        }
                    }else if(offsetX >llargada+estrella*3 && offsetX<= llargada+estrella*4 && !quart){
                        if (cinque) {
                            primer = true;
                            segon = true;
                            tercer = true;
                            quart = true;
                            cinque = true;
                            ViewProject.estat = 0;
                        }else{
                            primer = false;
                            segon = false;
                            tercer = false;
                            quart = false;
                            cinque = true;
                            ViewProject.estat=4;
                        }
                    }else{
                        primer = true;
                        segon = true;
                        tercer = true;
                        quart = true;
                        cinque = true;
                        ViewProject.estat = 0;
                    }
                }else if (offsetX > llargada &&  offsetX < llargada+estrella&&primer) {
                    tocat = true;
                    primer = false;
                    ViewProject.estat=1;
                } else if (offsetX > llargada+estrella && offsetX < llargada+estrella*2 &&segon) {
                    tocat = true;
                    primer = false;
                    segon = false;
                    ViewProject.estat=2;
                } else if (offsetX > llargada+estrella*2 && offsetX < llargada+estrella*3 && tercer) {
                    tocat = true;
                    primer = false;
                    segon = false;
                    tercer = false;
                    ViewProject.estat=3;
                } else if (offsetX > llargada+estrella*3 && offsetX < llargada+estrella*4 && quart) {
                    tocat = true;
                    primer = false;
                    segon = false;
                    tercer = false;
                    quart = false;
                    ViewProject.estat=4;
                } else if (offsetX > llargada+estrella*4 && offsetX < llargada+estrella*5 && cinque) {
                    tocat = true;
                    primer = false;
                    segon = false;
                    tercer = false;
                    quart = false;
                    cinque = false;
                    ViewProject.estat=5;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                x = initialX + event.getX();
                y = initialY + event.getY();

                if (x > llargada && x < llargada+estrella && initialX < offsetX&&initialX<x) {
                    tocat = true;
                    primer = false;
                    ViewProject.estat=1;
                } else if (x > llargada+estrella && x < llargada+estrella*2 && initialX < offsetX&&initialX<x) {
                    tocat = true;
                    primer = false;
                    segon = false;
                    ViewProject.estat=2;
                } else if (x > llargada+estrella*2 && x < llargada+estrella*3 && initialX < offsetX&&initialX<x) {
                    tocat = true;
                    primer = false;
                    segon = false;
                    tercer = false;
                    ViewProject.estat=3;
                } else if (x > llargada+estrella*3 && x < llargada+estrella*4 && initialX < offsetX&&initialX<x) {
                    tocat = true;
                    primer = false;
                    segon = false;
                    tercer = false;
                    quart = false;
                    ViewProject.estat=4;
                } else if (x > llargada+estrella*4 && x < llargada+estrella*5 && initialX < offsetX &&initialX<x) {
                    tocat = true;
                    primer = false;
                    segon = false;
                    tercer = false;
                    quart = false;
                    cinque = false;
                    ViewProject.estat=5;
                }
                if(x<llargada||x>llargada+estrella*6){
                        tocat=true;
                        primer=true;
                        segon=true;
                        tercer=true;
                        quart=true;
                        cinque=true;
                    ViewProject.estat=0;
                }else if(x<llargada+estrella){
                    tocat=true;
                    primer=false;
                    segon=true;
                    tercer=true;
                    quart=true;
                    cinque=true;
                    ViewProject.estat=1;
                }else if(x<llargada+estrella*2){
                    tocat=true;
                    primer=false;
                    segon=false;
                    tercer=true;
                    quart=true;
                    cinque=true;
                    ViewProject.estat=2;
                }else if(x<llargada+estrella*3){
                    tocat=true;
                    primer=false;
                    segon=false;
                    tercer=false;
                    quart=true;
                    cinque=true;
                    ViewProject.estat=3;
                }else if(x<llargada+estrella*4){
                    tocat=true;
                    primer=false;
                    segon=false;
                    tercer=false;
                    quart=false;
                    cinque=true;
                    ViewProject.estat=4;
                }else if(x<llargada+estrella*5){
                    tocat=true;
                    primer=false;
                    segon=false;
                    tercer=false;
                    quart=false;
                    cinque = false;
                    ViewProject.estat=5;
                }else if (x>llargada+estrella*5 && cinque) {
                    tocat = true;
                    primer = true;
                    segon = true;
                    tercer = true;
                    quart = true;
                    cinque = true;
                    ViewProject.estat = 0;
                }
            case MotionEvent.ACTION_CANCEL: {
                break;
            }
            case MotionEvent.ACTION_UP:
                tocat=false;
                break;
        }
        invalidate();
        return true;
    }
}
