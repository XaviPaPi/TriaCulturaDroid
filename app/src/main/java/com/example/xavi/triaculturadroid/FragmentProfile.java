package com.example.xavi.triaculturadroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xavi.triaculturadroid.Data.Model.User;
import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;

import org.w3c.dom.Text;


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
    PopupWindow passPopUp;
    Button btn_aceptar;
    Button btn_cancel;


    public FragmentProfile() {
        // Required empty public constructor
    }

    private static final String ARG_SECTION_NUMBER = "section_number";
    TextView tv_dni, tv_name, tv_email;
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
//        user = (userTransfer) getActivity().getIntent().getExtras().getSerializable("Usuari");
        if (getArguments() != null) {

        }
        user = new userTransfer(APIUtils.get_user_by_dni(getActivity().getIntent().getExtras().getString("Usuari")));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_profile, container, false);
        rootView.findViewById(R.id.btn_change_mail).setOnClickListener(change_mail_click);
        rootView.findViewById(R.id.btn_change_pass).setOnClickListener(change_pass_click);
        tv_dni = (TextView) rootView.findViewById(R.id.tv_user_dni);
        tv_name = (TextView) rootView.findViewById(R.id.tv_user_nom);
        tv_email = (TextView) rootView.findViewById(R.id.tv_label_mail);


        tv_dni.setText(user.getDni());
        tv_email.setText(user.getEmail());
        tv_name.setText(user.getName());

        return rootView;
    }

    public View.OnClickListener change_pass_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            View passView;
            LayoutInflater inflater;

            inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            passView = inflater.inflate(R.layout.dialogstyle, null);
            passPopUp = new PopupWindow(passView, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);

            //Mostrar PopUpPasswaord

            passPopUp.showAtLocation(view, Gravity.CENTER, 0, 0);

            //Declarar Buttons del PopUp

            btn_aceptar = (Button) passView.findViewById(R.id.DS_btn_aceptar_pass);
            btn_cancel = (Button) passView.findViewById(R.id.DS_btn_cancel_pass);

            btn_aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    upDatePass(user);
                }
            });
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Tancar el PopUp

                    passPopUp.dismiss();
                }
            });


            //TODO: CREAR DIÁLOGO PARA RELLENAR NUEVO MAIL
        }
    };

    public View.OnClickListener change_mail_click = new View.OnClickListener() {
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

    private void upDatePass(userTransfer u) {
        APIUtils.update_user(new User(u));
    }
}
