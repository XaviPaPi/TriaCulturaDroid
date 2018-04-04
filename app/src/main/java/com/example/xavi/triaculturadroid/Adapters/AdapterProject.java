package com.example.xavi.triaculturadroid.Adapters;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Data.Model.File;
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
    int id_vote;

    TextView LIP_textAuthor, LIP_textDescript, LIP_textTitle, LIP_textDescriptComplert;
    Button LIP_btnVote;
    static boolean votat;
    static int idProjecVotat;
    static Vote voteUser;
    static ImageView LIP_image1,LIP_image2,LIP_image3;
    ArrayList<Button> arrButons;

    public AdapterProject(Context context, List<Project> model, userTransfer user) {
        arrButons = new ArrayList<>();
        this.user = user;
        this.model = model;
        mode = 1;
        this.context = context;

        votes = APIUtils.get_votes(user.getId());
        if (votes != null && votes.size() > 0) {
            Vote vote = votes.get(votes.size() - 1);
            for (Project p : model)
                if (p.getId() == vote.getProj_id()) {
                    votat = true;
                    idProjecVotat = p.getId();
                    id_vote= vote.getId();
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
        LIP_textTitle = (TextView) convertView.findViewById(R.id.ILP_Title);
        LIP_textDescript = (TextView) convertView.findViewById(R.id.ILP_DescriptionLimitat);
        LIP_textDescriptComplert = (TextView) convertView.findViewById(R.id.ILP_DescriptionComplert);
        LIP_textAuthor = (TextView) convertView.findViewById(R.id.ILP_AuthorName);
        LIP_btnVote = (Button) convertView.findViewById(R.id.ILP_Bnt_vote);
        LIP_image1 = (ImageView) convertView.findViewById(R.id.ILP_imageView1);
        LIP_image2 = (ImageView) convertView.findViewById(R.id.ILP_imageView2);
        LIP_image3 = (ImageView) convertView.findViewById(R.id.ILP_imageView3);


        if(!arrButons.contains(LIP_btnVote))
            arrButons.add(LIP_btnVote);


        LIP_textTitle.setText(model.get(position).getTitle());
        LIP_textDescript.setText(model.get(position).getDescript());
        LIP_textDescriptComplert.setText(model.get(position).getDescript());
        LIP_textAuthor.setText(model.get(position).getAuthor().getName());


        for (int i = 0; i < model.get(position).getFiles().size(); i++) {
            byte[] imageBytes =APIUtils.get_file_from_id(model.get(position).getFiles().get(i).getId()).getFile_content();
            Log.d(TAG, "getView: "+imageBytes);
            if (model.get(position).getFiles().get(i).getExtension().equals(".jpg")){
                Bitmap image = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                switch (i){
                    case 0:
                        LIP_image1.setImageBitmap(image);
                        break;
                    case 1:
                        LIP_image2.setImageBitmap(image);
                        break;
                    case 2:
                        LIP_image3.setImageBitmap(image);
                        break;
                }
            }else{
                switch (i){
                    case 0:
                        LIP_image1.setVisibility(View.GONE);
                        break;
                    case 1:
                        LIP_image2.setVisibility(View.GONE);
                        break;
                    case 2:
                        LIP_image3.setVisibility(View.GONE);
                        break;
                }
            }
        }
        if (votat && idProjecVotat != model.get(position).getId()) {
            LIP_btnVote.setEnabled(false);
        }else{
            LIP_btnVote.setEnabled(true);
        }


        final View finalConvertView = convertView;
        LIP_textDescript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LIP_textDescript = (TextView) finalConvertView.findViewById(R.id.ILP_DescriptionLimitat);
                LIP_textDescriptComplert = (TextView) finalConvertView.findViewById(R.id.ILP_DescriptionComplert);
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

                if (!votat) {
                    for (int i = 0; i < arrButons.size(); i++) {
                        if (i != position) {
                            arrButons.get(i).setEnabled(false);
                            idProjecVotat=model.get(position).getId();
                        }
                    }
                    voteUser = APIUtils.post_new_vote(vote);
                    votat = true;
                } else {
                    vote = APIUtils.get_vote(vote.getUser_id(),vote.getProj_id());
                    APIUtils.delete_vote(vote);
                    votat = false;
                    for (int i = 0; i < arrButons.size(); i++) {
                        if (i != position) {
                            arrButons.get(i).setEnabled(true);
                        }
                    }
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
