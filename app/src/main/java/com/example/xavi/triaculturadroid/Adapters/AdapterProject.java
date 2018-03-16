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
import com.example.xavi.triaculturadroid.Data.Model.User;
import com.example.xavi.triaculturadroid.Data.Model.Vote;
import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;
import com.example.xavi.triaculturadroid.R;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by miquel on 2/22/2018.
 */

public class AdapterProject extends BaseAdapter {

    List<Project> model;
    List<Vote> votes;
    Context context;
    Project projct;
    userTransfer user;
    int mode;
    TextView LIP_textAuthor, LIP_textDescript, LIP_textTitle,LIP_textDescriptComplert;
    Button LIP_btnVote;
    static boolean votat;
    static int idProjecVotat;
    static Vote voteUser;
    ArrayList<Button> arrButons;
    public AdapterProject(Context context, List<Project> model,userTransfer user) {
        arrButons= new ArrayList<>();
        this.user = user;
        this.model = model;
        mode = 1;
        this.context = context;

        votes = APIUtils.get_votes(user.getId());
        if (votes!=null&&votes.size()>0) {
            Vote vote = votes.get(votes.size() - 1);
            if (model.contains(vote.getProject())) {
                votat = true;
                idProjecVotat = vote.getProject().getId();
            }
        }

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

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.activity_item_list_projects, parent, false);
        }

        //Project item = model.get(position);
        final LinearLayout LIP_LayoutPrincip = (LinearLayout) convertView.findViewById(R.id.P_LinearGeneral);
        LIP_textTitle = (TextView) convertView.findViewById(R.id.ILP_Title);
        LIP_textDescript = (TextView) convertView.findViewById(R.id.ILP_DescriptionLimitat);
        LIP_textDescriptComplert = (TextView) convertView.findViewById(R.id.ILP_DescriptionComplert);
        LIP_textAuthor = (TextView) convertView.findViewById(R.id.ILP_AuthorName);
        LIP_btnVote = (Button) convertView.findViewById(R.id.ILP_Bnt_vote);
        arrButons.add(LIP_btnVote);

        LIP_textTitle.setText(model.get(position).getTitle());
        LIP_textDescript.setText(model.get(position).getDescript());
        LIP_textDescriptComplert.setText(model.get(position).getDescript());
        LIP_textAuthor.setText(model.get(position).getAuthor().getName());


        votes = APIUtils.get_votes(user.getId());
        if (votes!=null&&votes.size()>0) {
            Vote vote = votes.get(votes.size() - 1);
            if (model.contains(vote.getProject())) {
                votat = true;
                idProjecVotat = vote.getProject().getId();
            }
        }


        if (votat&&idProjecVotat!=position){
            LIP_btnVote.setEnabled(false);
        }


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
                Vote vote = new Vote();
                vote.setProj_id(model.get(position).getId());
                vote.setUser_id(user.getId());
                vote.setDateVote(String.valueOf(DateFormat.getDateInstance()));
                if (!votat) {
                    voteUser = APIUtils.post_new_vote(vote);
                    for (int i = 0; i < arrButons.size(); i++) {
                        if (i != position) {
                            arrButons.get(i).setEnabled(false);
                        }
                    }
                    votat=true;
                }else{
                    APIUtils.delete_vote(vote);
                    votat=false;
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

