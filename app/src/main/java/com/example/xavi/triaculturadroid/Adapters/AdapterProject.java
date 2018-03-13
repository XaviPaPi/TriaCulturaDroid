package com.example.xavi.triaculturadroid.Adapters;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.R;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by miquel on 2/22/2018.
 */

public class AdapterProject extends BaseAdapter {
    private List<Project> model;
    private Context context;
    private Project projct;
    private int mode;
    private TextView LIP_textAuthor, LIP_textDescript, LIP_textTitle,LIP_textDescriptComplert;
    private Button LIP_btnVote;
    private int dissable=-1;
 //   private Collection<Button> collection;
    public AdapterProject(Context context, List<Project> model) {
        this.model = model;
        mode = 1;
        this.context = context;
        Log.d(TAG, "ADAPTER COUNT: " + model.size());
    }

    public AdapterProject(Context context, Project project) {
        this.projct = project;
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
        if (mode == 1)
            return getItem(position).getId();
        else
            return 0;
    }
    LayoutInflater inflator;
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            inflator= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.activity_item_list_projects, parent, false);
        }

        //Project item = model.get(position);
        final LinearLayout LIP_LayoutPrincip = (LinearLayout) convertView.findViewById(R.id.P_LinearGeneral);
        LIP_textTitle = (TextView) convertView.findViewById(R.id.ILP_Title);
        LIP_textDescript = (TextView) convertView.findViewById(R.id.ILP_DescriptionLimitat);
        LIP_textDescriptComplert = (TextView) convertView.findViewById(R.id.ILP_DescriptionComplert);
        LIP_textAuthor = (TextView) convertView.findViewById(R.id.ILP_AuthorName);
        LIP_btnVote = (Button) convertView.findViewById(R.id.ILP_Bnt_vote);
       // collection.add(LIP_btnVote);

        LIP_textTitle.setText(model.get(position).getTitle());
        LIP_textDescript.setText(model.get(position).getDescript());
        LIP_textDescriptComplert.setText(model.get(position).getDescript());
        LIP_textAuthor.setText(model.get(position).getAuthor().getName());

        //solo funciona si el item no se esta viendo.

       /* if (dissable>-1&&dissable!=position){
            LIP_btnVote.setEnabled(false);
        }else{

        }*/



        final View finalConvertView = convertView;
        LIP_textDescript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LIP_textDescript = (TextView) finalConvertView.findViewById(R.id.ILP_DescriptionLimitat);
                LIP_textDescriptComplert = (TextView)finalConvertView.findViewById(R.id.ILP_DescriptionComplert);
                LIP_textDescript.setVisibility(View.GONE);
                LIP_textDescriptComplert.setVisibility(View.VISIBLE);
            }
        });
        LIP_textDescriptComplert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LIP_textDescript = (TextView) finalConvertView.findViewById(R.id.ILP_DescriptionLimitat);
                LIP_textDescriptComplert = (TextView) finalConvertView.findViewById(R.id.ILP_DescriptionComplert);
                LIP_textDescript.setVisibility(View.VISIBLE);
                LIP_textDescriptComplert.setVisibility(View.GONE);
            }
        });
        LIP_btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dissable==position){

                }else {
                    dissable = position;
                }
            }
        });
        return convertView;
    }
    private void dissableButtons(){

    }




   /* private void on_click_descriptionLimitat(int pos, View v) {
        Log.d(TAG, "onClick: " + pos);
        //LIP_textTitle = (TextView) v.findViewById(R.id.ILP_Title);
        LIP_textDescript = (TextView) v.findViewById(R.id.ILP_DescriptionLimitat);
        LIP_textDescriptComplert = (TextView) v.findViewById(R.id.ILP_DescriptionComplert);
        //LIP_btnVote = (Button) v.findViewById(R.id.ILP_Bnt_vote);
        //v.setVisibility(View.GONE);
        LIP_textDescript.setVisibility(View.GONE);
        LIP_textDescriptComplert.setVisibility(View.VISIBLE);

    }
    private void on_click_descriptionComplert(int pos, View v) {
        Log.d(TAG, "onClick: " + pos);
        v.setVisibility(View.GONE);
        LIP_textDescript.setVisibility(View.VISIBLE);
    }
    private void on_click_add_vote(int pos, View v) {
        /*for (int i=0; i<model.size(); i++){
            if (pos != i){

            }else{

            }
        }

    }*/



    @Override
    public boolean hasStableIds() {
        return true;
    }
}

