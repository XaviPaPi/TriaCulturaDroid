package com.example.xavi.triaculturadroid.Adapters;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.xavi.triaculturadroid.Data.Model.Historial;
import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Model.User;
import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.R;

import java.util.List;


public class AdapterHistorial extends BaseAdapter {

    TextView LHP_data, LHP_nom_Projecte, LHP_vots;
    ImageView LHP_valoracio;
    Context context;
    userTransfer user;
    List<Historial> list_histo;
    private Historial histo;
    int mode;

    public AdapterHistorial(Context context, List<Historial> list_histo){
        this.context = context;
        this.list_histo = list_histo;
        mode =1;
    }


    @Override
    public int getCount() {
        if (mode == 1)
            return list_histo.size();
        else
            return 1;
    }

    @Override
    public Historial getItem(int position) {
        if (mode == 1) {
            return list_histo.get(position);
        } else {
            return histo;
        }
    }

    @Override
    public long getItemId(int position) {
        if (mode == 1)
            return getItem(position).getId();
        else
            return 0;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.activity_item_historial_projects, parent, false);
        }
        int id_project;
        LHP_valoracio = (ImageView)convertView.findViewById(R.id.img_thumbs);
        LHP_data = (TextView) convertView.findViewById(R.id.IHP_data);
        LHP_nom_Projecte = (TextView) convertView.findViewById(R.id.IHP_nom_projecte);

        LHP_data.setText(list_histo.get(position).getData().substring(0,10));
        LHP_nom_Projecte.setText(list_histo.get(position).getTitle());
        double punts =  Math.round(list_histo.get(position).getRating());




        String puntuacio=String.valueOf(punts);
        switch (puntuacio){
            case "0.0":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_greys));
                break;
            case "0.5":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_grey_1_2star));
                break;
            case "1.0":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_grey_1star));
                break;
            case "1.5":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_grey_2_2star));
                break;
            case "2.0":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_grey_2star_));
                break;
            case "2.5":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_grey_3_2star_));
                break;
            case "3.0":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_grey_3star_));
                break;
            case "3.5":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_grey_4_2star));
                break;
            case "4.0":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_grey_4star));
                break;
            case "4.5":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_grey_5_2star));
                break;
            case "5.0":
                LHP_valoracio.setImageDrawable( context.getResources().getDrawable(R.drawable.star_grey_5star));
                break;
        }
        return convertView;
    }
}
