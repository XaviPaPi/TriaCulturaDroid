package com.example.xavi.triaculturadroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Data.Model.Profile_Historial;
import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Model.Request;
import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;
import com.example.xavi.triaculturadroid.R;

import java.util.List;

public class AdapterHistoProfile extends BaseAdapter {

    TextView LHP_data, LHP_nom_Projecte, LHP_description;
    //ImageView thumbs;
    Context context;
    Request r;
    userTransfer user;
    List<Profile_Historial> list_histo;
    private Profile_Historial histo;
    int mode;

    public AdapterHistoProfile(Context context, List<Profile_Historial> list_histo){
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
    public Profile_Historial getItem(int position) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.activoty_item_historial_profile, parent, false);
        }

        int id_project = list_histo.get(position).getId();
        Project p = APIUtils.get_Project(id_project);
//        LHP_valoracio = (ImageView)convertView.findViewById(R.id.IHP_stars);
        LHP_data = (TextView) convertView.findViewById(R.id.date_project_profile);
       // LHP_description = (TextView) convertView.findViewById(R.id.Description_profileVotes);
        LHP_nom_Projecte = (TextView) convertView.findViewById(R.id.Title_project_profileVotes);
//        thumbs = (ImageView) convertView.findViewById((R.id.img_thumb));

        LHP_nom_Projecte.setText(p.getTitle());
        //LHP_description.setText(p.getDescript());
        LHP_data.setText(p.getRequests().get(position).getData_proposta());


//        List<Request> lr = p.getRequests();
//        for (Request r: lr) {
//
//            if(r.isEsGuanyador()==1){
//                thumbs.setImageDrawable(context.getResources().getDrawable(R.drawable.good_thumb));
//            }
//
//            if(r.isEsGuanyador()==0){
//                thumbs.setImageDrawable(context.getResources().getDrawable(R.drawable.fail_thumb));
//            }
//        }




//        boolean WinOrLoseRequest =  Math.floor(list_histo.get(position).getRating());
        return convertView;
    }
}
