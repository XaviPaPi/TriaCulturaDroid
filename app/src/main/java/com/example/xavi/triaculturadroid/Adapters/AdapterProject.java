package com.example.xavi.triaculturadroid.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.R;

import java.util.List;

/**
 * Created by miquel on 2/22/2018.
 */

public class AdapterProject extends BaseAdapter {
    List<Project> model;
    Context context;
    Project projct;
    int mode;
    TextView LIP_textAuthor,LIP_textDescript,LIP_textTitle;
    Button LIP_btnVote;

    public AdapterProject(Context context, List<Project> model) {
        this.model = model;
        mode = 1;
        this.context = context;
    }

    public AdapterProject(Context context, Project projct) {
        this.projct = projct;
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
            return projct;
        }
    }

    @Override
    public long getItemId(int position) {
        if (mode ==1)
            return getItem(position).getId();
        else
            return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.activity_item_list_projects, parent, false);
        }
        //Project item = model.get(position);
        final LinearLayout LIP_LayoutPrincip = (LinearLayout) convertView.findViewById(R.id.P_LinearGeneral);
        LIP_textTitle = (TextView) convertView.findViewById(R.id.ILP_Title);
        LIP_textDescript = (TextView) convertView.findViewById(R.id.ILP_Description);
        LIP_textAuthor= (TextView) convertView.findViewById(R.id.ILP_AuthorName);
        LIP_btnVote = (Button) convertView.findViewById(R.id.ILP_Bnt_vote);

        LIP_btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LIP_textTitle.setVisibility(View.GONE);
            }
        });

        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }
}

