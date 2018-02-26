package com.example.xavi.triaculturadroid;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Adapters.AdapterProject;
import com.example.xavi.triaculturadroid.Model.Project;

import java.util.ArrayList;


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
    public ProjectFragment() {
        // Required empty public constructor
    }
    private static final String ARG_SECTION_NUMBER = "section_number";
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
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

        listView =(ListView) rootView.findViewById(R.id.ListProjects);
        ArrayList<Project> ad= new ArrayList<>();
        Project c = new Project(1,"Titol","???","Hola que tal");
        Project c2 = new Project(2,"Titol","???","Hola que tal");
        Project c3 = new Project(3,"Titol","???","Hola que tal");
        Project c4 = new Project(4,"Titol","???","Hola que tal");
        Project c5 = new Project(5,"Titol","???","Hola que tal");
        ad.add(c);
        ad.add(c2);
        ad.add(c3);
        ad.add(c4);
        ad.add(c5);
        AdapterProject adapter = new AdapterProject(getActivity(),ad);
        //listView.setItemsCanFocus(true);
        listView.setAdapter(adapter);



        return rootView;
    }

}
