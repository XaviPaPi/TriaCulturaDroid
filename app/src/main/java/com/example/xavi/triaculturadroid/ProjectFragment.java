package com.example.xavi.triaculturadroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xavi.triaculturadroid.Adapters.AdapterProject;
import com.example.xavi.triaculturadroid.Data.Model.Project;

import java.util.ArrayList;

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
        Project c = new Project(1,"Ttol","???","Hola que tal","Fals",3);
        Project c2 = new Project(2,"itol","???!","Hola que","Erroni",2);
        Project c3 = new Project(3,"tol","??!?","Hola tal","Tautològic",1);
        Project c4 = new Project(4,"l","?!??","Hola","Falaç",4);
        Project c5 = new Project(5,"Titol_2","!???","que tal","Vertader",5);
        ad.add(c);
        ad.add(c2);
        ad.add(c3);
        ad.add(c4);
        ad.add(c5);
        Log.d(TAG, "onCreateView: "+ad.size());
        AdapterProject adapter = new AdapterProject(getActivity(),ad);
        listView.setItemsCanFocus(true);
        listView.setAdapter(adapter);



        return rootView;
    }

}
