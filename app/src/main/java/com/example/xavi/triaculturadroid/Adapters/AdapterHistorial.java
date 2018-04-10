package com.example.xavi.triaculturadroid.Adapters;

import android.content.Context;
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
import com.example.xavi.triaculturadroid.R;

import java.util.List;


public class AdapterHistorial extends BaseAdapter {

    TextView LHP_data, LHP_nom_Projecte, LHP_vots;
    ImageView valoracio;
    Context context;
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

        LHP_data = (TextView) convertView.findViewById(R.id.IHP_data);
        LHP_nom_Projecte = (TextView) convertView.findViewById(R.id.IHP_nom_projecte);
        LHP_vots = (TextView) convertView.findViewById(R.id.IHP_valoracio);

        LHP_data.setText(list_histo.get(position).getData());
        LHP_nom_Projecte.setText(list_histo.get(position).getTitle());
        LHP_vots.setText("" + list_histo.get(position).getRating());



        return convertView;
    }
}
