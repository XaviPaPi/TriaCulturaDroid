package com.example.xavi.triaculturadroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xavi.triaculturadroid.Adapters.AdapterProject;
import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;
import com.example.xavi.triaculturadroid.Data.Remote.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectFragment} interface
 * to handle interaction events.
 * Use the {@link ProjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectFragment extends Fragment {
    private ListView listView;
    List<Project> ad;

    public ProjectFragment() {
        // Required empty public constructor
    }

    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectFragment newInstance(int sectionNumber) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_project, container,
                false);

        listView = (ListView) rootView.findViewById(R.id.ListProjects);
        ad = new ArrayList<>();

        ad = APIUtils.get_projects_from_place(6);


        AdapterProject adapter = new AdapterProject(getActivity(), ad);

        listView.setItemsCanFocus(true);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project projct;
                int mode;
                final TextView LIP_textAuthor, LIP_textDescript, LIP_textTitle, LIP_textDescriptComplert;
                final Button LIP_btnVote;

                //Project item = model.get(position);
                final LinearLayout LIP_LayoutPrincip = (LinearLayout) parent.findViewById(R.id.P_LinearGeneral);
                LIP_textTitle = (TextView) parent.findViewById(R.id.ILP_Title);
                LIP_textDescript = (TextView) parent.findViewById(R.id.ILP_DescriptionLimitat);
                LIP_textDescriptComplert = (TextView) parent.findViewById(R.id.ILP_DescriptionComplert);
                LIP_textAuthor = (TextView) parent.findViewById(R.id.ILP_AuthorName);
                LIP_btnVote = (Button) parent.findViewById(R.id.ILP_Bnt_vote);


              /*  LIP_textTitle.setText(ad.get(position).getTitle());
                LIP_textDescript.setText(ad.get(position).getDescript());
                LIP_textDescriptComplert.setText(ad.get(position).getDescript());
                LIP_textAuthor.setText(ad.get(position).getAuthor().getName());*/


                LIP_textDescript.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //on_click_descriptionLimitat(position, view);
                        LIP_textDescript.setVisibility(View.GONE);
                        LIP_textDescriptComplert.setVisibility(View.VISIBLE);
                    }
                });
                LIP_textDescriptComplert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // on_click_descriptionComplert(position, view);
                        LIP_textDescript.setVisibility(View.VISIBLE);
                        LIP_textDescriptComplert.setVisibility(View.GONE);
                    }
                });
            }
        });

        return rootView;
    }

}
