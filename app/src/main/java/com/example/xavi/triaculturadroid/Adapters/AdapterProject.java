package com.example.xavi.triaculturadroid.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Model.Project;
import com.example.xavi.triaculturadroid.R;

import java.util.List;

/**
 * Created by miquel on 2/22/2018.
 */

public class AdapterProject extends BaseAdapter {
    LayoutInflater inflater;
    List<Project> model;
    Context context;
    Project pers;
    int mode;
    EditText nameText;
    EditText surNameText;
    EditText descripcioText;
    TextView nameView;
    TextView surnameView;
    TextView descripcioView;
    public AdapterProject(Context context, List<Project> model) {
        this.model = model;
        mode = 1;
        this.context = context;
    }

    public AdapterProject(Context context, Project pers) {
        this.pers = pers;
        this.context = context;
        mode = 0;
    }

    @Override
    public int getCount() {
        if (mode == 1)
            return model.size();
        else
            return 1;
    }

    @Override
    public Project getItem(int position) {
        if (mode == 1) {
            return model.get(position);
        } else {
            return pers;
        }
    }

    @Override
    public long getItemId(int position) {
        if (mode == 1)
            return getItem(position).getId();
        else
            return 1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.activity_item_list_projects, parent, false);
        }
        return convertView;
    }

}

