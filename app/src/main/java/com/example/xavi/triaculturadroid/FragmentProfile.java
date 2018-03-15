package com.example.xavi.triaculturadroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentProfile} interface
 * to handle interaction events.
 * Use the {@link FragmentProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentProfile extends Fragment {

    String new_pass;
    String confirm_pass;

    public FragmentProfile() {
        // Required empty public constructor
    }
    private static final String ARG_SECTION_NUMBER = "section_number";
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
            show_change_pass_dialog(view);
        }
    };

    public void show_change_pass_dialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Canviar Contrasenya");

// Set up the input
        final EditText input = new EditText(v.getContext());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new_pass = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
