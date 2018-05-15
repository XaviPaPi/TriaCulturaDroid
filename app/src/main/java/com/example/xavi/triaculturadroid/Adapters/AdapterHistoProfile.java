package com.example.xavi.triaculturadroid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Data.Model.Profile_Historial;
import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Model.Request;
import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;
import com.example.xavi.triaculturadroid.ProfileVotes;
import com.example.xavi.triaculturadroid.R;

import java.util.List;

public class AdapterHistoProfile extends BaseAdapter {

    TextView LHP_data, LHP_nom_Projecte, LHP_description;
    //ImageView thumbs;
    Context context;
    Request r;
    ImageView thumbs;
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
        Profile_Historial ph = list_histo.get(position);

        LHP_data = (TextView) convertView.findViewById(R.id.date_project_profile);

        LHP_nom_Projecte = (TextView) convertView.findViewById(R.id.Title_project_profileVotes);
        thumbs = (ImageView) convertView.findViewById((R.id.img_thumbs));

        LHP_nom_Projecte.setText(p.getTitle());
        LHP_data.setText(ph.getData().substring(0,10));

//        List<Request> lr = ph.getRequests();
//        for (Request r: lr) {

            if(ph.isWinOrLose()){
                thumbs.setImageDrawable(context.getResources().getDrawable(R.drawable.good_thumb));
            } else {
                thumbs.setImageDrawable(context.getResources().getDrawable(R.drawable.fail_thumb));
            }
//        }




//        boolean WinOrLoseRequest =  Math.floor(list_histo.get(position).getRating());
        return convertView;
    }
}
