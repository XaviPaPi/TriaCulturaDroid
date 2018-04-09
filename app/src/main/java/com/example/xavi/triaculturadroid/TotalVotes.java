package com.example.xavi.triaculturadroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.xavi.triaculturadroid.Adapters.AdapterHistorial;
import com.example.xavi.triaculturadroid.Adapters.AdapterProject;
import com.example.xavi.triaculturadroid.Data.Model.Historial;
import com.example.xavi.triaculturadroid.Data.Model.Project;
import com.example.xavi.triaculturadroid.Data.Model.Request;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TotalVotes} interface
 * to handle interaction events.
 * Use the {@link TotalVotes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TotalVotes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private ListView historialList;

    List<Historial> historial_Projects_List;
    List<Project> project_List;
    Historial historial;

    String data;
    String title;
    double rating_from_Service;

    // TODO: Rename and change types of parameters
    public TotalVotes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TotalVotes.
     */
    // TODO: Rename and change types and number of parameters
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static TotalVotes newInstance(int sectionNumber) {
        TotalVotes fragment = new TotalVotes();
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
        return inflater.inflate(R.layout.activity_item_historial_projects, container, false);

//        historialList = (ListView) viewHistorial.findViewById(R.id.ListVotes);

//        List<Request> request_List = APIUtils.get_winning_requests();
//        for (Request r : request_List) {
//            historial = new Historial();
//            historial.setRating(APIUtils.get_project_avg(r.getProject()));
//            historial.setData(r.getData_proposta());
//            historial.setTitle(r.getProject().getTitle());
//
//            historial_Projects_List.add(historial);
//        }
//
//
//        AdapterHistorial adapter = new AdapterHistorial(getActivity(), historial_Projects_List);
//
//        historialList.setItemsCanFocus(true);
//        historialList.setAdapter(adapter);

//        return viewHistorial;
    }

}
