package com.example.xavi.triaculturadroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Data.Model.userTransfer;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends Fragment {
    public FragmentProfile() {
        // Required empty public constructor
    }
    private static final String ARG_SECTION_NUMBER = "section_number";
    TextView tv_dni,tv_name,tv_email;
    userTransfer user;
    public static FragmentProfile newInstance(int sectionNumber) {
        FragmentProfile fragment = new FragmentProfile();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (userTransfer) getActivity().getIntent().getExtras().getSerializable("Usuari");
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_profile, container, false);
        rootView.findViewById(R.id.btn_change_mail).setOnClickListener(change_mail_click);
        rootView.findViewById(R.id.btn_change_pass).setOnClickListener(change_pass_click);
        tv_dni= (TextView)rootView.findViewById(R.id.tv_user_dni);
        tv_name= (TextView)rootView.findViewById(R.id.tv_user_nom);
        tv_email= (TextView)rootView.findViewById(R.id.tv_label_mail);

        tv_dni.setText(user.getDni());

        return rootView;
    }

    public View.OnClickListener change_mail_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: CREAR DIÁLOGO PARA RELLENAR NUEVO MAIL
        }
    };

    public View.OnClickListener change_pass_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: CREAR DIÁLOGO PARA RELLENAR NUEVA CONTRASEÑA
        }
    };
}
