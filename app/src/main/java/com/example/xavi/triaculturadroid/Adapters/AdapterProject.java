package com.example.xavi.triaculturadroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.ClassProjectProv;
import com.example.xavi.triaculturadroid.R;

import java.util.ArrayList;

/**
 * Created by miquel on 2/22/2018.
 */

public class AdapterProject extends ArrayAdapter<ClassProjectProv> {
    LayoutInflater inflater;
    public AdapterProject(Context context, ArrayList<ClassProjectProv> users) {
        super(context, 0, users);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ClassProjectProv user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_item_list_projects, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.ILP_AuthorName);
        TextView tvDescriopt = (TextView) convertView.findViewById(R.id.ILP_Description);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.ILP_Title);
        // Populate the data into the template view using the data object
        tvName.setText(user.getName());
        tvTitle.setText(user.getTitle());
        tvDescriopt.setText(user.getDescript());
        // Return the completed view to render on screen
        return convertView;
    }
}

