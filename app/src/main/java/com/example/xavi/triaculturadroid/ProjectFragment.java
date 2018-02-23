package com.example.xavi.triaculturadroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xavi.triaculturadroid.Adapters.AdapterProject;

import java.util.ArrayList;
import java.util.List;


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
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_project, container,
                false);
        listView =(ListView)rootView.findViewById(R.id.ListProjects);
        String[] values = new String[] { "Message1", "Message2", "Message3" };
        ArrayList<ClassProjectProv> ad= new ArrayList<>();
        ClassProjectProv c = new ClassProjectProv(1,"Titol","???","Hola que tal");
        ClassProjectProv c2 = new ClassProjectProv(2,"Titol","???","Hola que tal");
        ClassProjectProv c3 = new ClassProjectProv(3,"Titol","???","Hola que tal");
        ClassProjectProv c4 = new ClassProjectProv(4,"Titol","???","Hola que tal");
        ClassProjectProv c5 = new ClassProjectProv(5,"Titol","???","Hola que tal");
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
