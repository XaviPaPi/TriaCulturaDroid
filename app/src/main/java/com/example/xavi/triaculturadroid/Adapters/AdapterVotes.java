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
import com.example.xavi.triaculturadroid.Data.Model.Vote;
import com.example.xavi.triaculturadroid.R;

import java.util.List;

/**
 * Created by miquel on 2/23/2018.
 */

public class AdapterVotes extends BaseAdapter {
    List<Vote> model;
    Context context;
    Vote vote;
    int mode;
    TextView LIP_textAuthor,LIP_textDescript,LIP_textTitle;
    Button LIP_btnVote;

    public AdapterVotes(Context context, List<Vote> model) {
        this.model = model;
        mode = 1;
        this.context = context;
    }

    public AdapterVotes(Context context, Vote vote) {
        this.vote = vote;
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
    public Vote getItem(int position) {
        if (mode == 1) {
            return model.get(position);
        } else {
            return vote;
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


        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return true;
    }
}
