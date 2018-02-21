package com.example.xavi.triaculturadroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectFragment} interface
 * to handle interaction events.
 * Use the {@link ProjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_project, container, false);
    }
}
