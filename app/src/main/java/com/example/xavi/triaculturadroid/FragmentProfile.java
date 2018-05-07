package com.example.xavi.triaculturadroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    String new_mail;
    String old_mail;
    PopupWindow passPopUp;
    Button btn_aceptarPass;
    Button btn_aceptarEmail;


    public FragmentProfile() {
        // Required empty public constructor
    }

    private static final String ARG_SECTION_NUMBER = "section_number";
    TextView tv_name, tv_email;
    EditText tv_oldPass;
    EditText tv_newPass1;
    EditText tv_newPass2;
    EditText tv_oldEmail;
    EditText tv_newEmail;
    ImageView iv_password,iv_mail;
    LinearLayout pasword, email;


    userTransfer userT;
    User user;

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
//        userT = (userTransfer) getActivity().getIntent().getExtras().getSerializable("Usuari");
        if (getArguments() != null) {

        }
        user = APIUtils.get_user_by_dni(getActivity().getIntent().getExtras().getString("Usuari"));
        userT = new userTransfer(user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_profile, container, false);
       // rootView.findViewById(R.id.btn_change_mail).setOnClickListener(change_mail_click);
       // rootView.findViewById(R.id.btn_change_pass).setOnClickListener(change_pass_click);
        rootView.findViewById(R.id.btn_see_votes).setOnClickListener(show_profile_votes);



        tv_name = (TextView) rootView.findViewById(R.id.tv_user_nom);
        tv_email = (TextView) rootView.findViewById(R.id.tv_label_mail);
        tv_oldPass = rootView.findViewById(R.id.oldpass);
        tv_newPass1 = rootView.findViewById(R.id.newpass1);
        tv_newPass2 = rootView.findViewById(R.id.newpass2);
        tv_oldEmail= rootView.findViewById(R.id.newMail);
        tv_newEmail = rootView.findViewById(R.id.newMail2);
        iv_password = rootView.findViewById(R.id.btn_change_pass);
        iv_mail = rootView.findViewById(R.id.btn_change_mail);
        pasword = rootView.findViewById(R.id.pasword_profile);
        email = rootView.findViewById(R.id.mail_profile);
        email.setVisibility(View.GONE);
        pasword.setVisibility(View.GONE);


        tv_oldEmail.setHint("Introdueix l'antic mail");
        tv_newEmail.setHint("Introdueix el nou mail");



        btn_aceptarPass = (Button) rootView.findViewById(R.id.DS_btn_aceptar_pass);
        btn_aceptarPass.setText("Accepta");
        btn_aceptarEmail = (Button) rootView.findViewById(R.id.DS_btn_aceptar_mail);
        btn_aceptarEmail.setText("Accepta");

        iv_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pasword.getVisibility()!=View.VISIBLE) {
                    pasword.setVisibility(View.VISIBLE);
                    email.setVisibility(View.GONE);
                }else{
                    pasword.setVisibility(View.GONE);
                }
            }
        });

        iv_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getVisibility()!=View.VISIBLE) {
                    email.setVisibility(View.VISIBLE);
                    pasword.setVisibility(View.GONE);
                }else{
                    email.setVisibility(View.GONE);
                }
            }
        });



        btn_aceptarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean canviat = check_pass(tv_oldPass.getText().toString(), tv_newPass1.getText().toString(), tv_newPass2.getText().toString());
              if (canviat) {
                  pasword.setVisibility(View.GONE);
              }
            }
        });
        btn_aceptarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((tv_oldEmail.getText().toString().length()>1&&tv_newEmail.getText().toString().length()>1)&&tv_oldEmail.getText().toString().equals(tv_newEmail.getText().toString())) {
                    user.setEmail(tv_newEmail.getText().toString());
                    APIUtils.update_user(user);
                    email.setVisibility(View.GONE);
                }
            }
        });

        tv_email.setText(userT.getEmail());
        tv_name.setText(userT.getName());

        UserSearch.tanca_dialog();
        return rootView;
    }

    public View.OnClickListener show_profile_votes = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent profileVotes = new Intent(getActivity() , ProfileVotes.class);
            profileVotes.putExtra("Usuari", userT.getId());
            startActivity(profileVotes);
        }
    };
/*
    public View.OnClickListener change_pass_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            View passView;
            LayoutInflater inflater;

            inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            passView = inflater.inflate(R.layout.dialogstyle, null);
            passPopUp = new PopupWindow(passView, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            passPopUp.setFocusable(true);
            passPopUp.update();
            //Mostrar PopUpPasswaord

            passPopUp.showAtLocation(view, Gravity.CENTER, 0, 0);

            //Declarar Buttons del PopUp




            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Tancar el PopUp
                    passPopUp.dismiss();
                }
            });

        }
    };
*/
    private boolean check_pass(String old_pass, String new_pass_a, String new_pass_b) {
        boolean new_pass_equals = false;
        boolean old_pass_is_correct = false;
        // Check new pass a/b
        new_pass_equals = new_pass_a.equals(new_pass_b);
        if (new_pass_equals) {
            old_pass_is_correct = user.getPassword().equals(old_pass);
            if (old_pass_is_correct) {
                user.setPassword(new_pass_a);
                updateUser(user);
                return true;
            } else {
                Toast.makeText(this.getContext(), "Wrong current password", Toast.LENGTH_SHORT).show();
            }
        } else {
        Toast.makeText(this.getContext(), "New Passwords don't match", Toast.LENGTH_SHORT).show();
        }
        return false;

    }
/*
    public View.OnClickListener change_mail_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            show_change_pass_dialog(view);
        }
    };

    public void show_change_pass_dialog(View v) {

        View passView;
        LayoutInflater inflater;

        inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        passView = inflater.inflate(R.layout.dialogstyle, null);
        passPopUp = new PopupWindow(passView, RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        passPopUp.setFocusable(true);
        passPopUp.update();
        //Mostrar PopUpPasswaord

        passPopUp.showAtLocation(v, Gravity.CENTER, 0, 0);

        //Declarar Buttons del PopUp

        btn_aceptar = (Button) passView.findViewById(R.id.DS_btn_aceptar_pass);
        btn_aceptar.setText("Accepta");



        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean changed = check_pass(tv_oldPass.getText().toString(), tv_newPass1.getText().toString(), tv_newPass2.getText().toString());

                // TODO: Pensar si userTransfer es necesario realmente...
                // Es posible que podamos poner el user como static en la TabbetsActivity y nos sirva para everything
                // al cargar la TabbetsActivity, guardándonos en SharedPreferences o en el Intent el dni, podemos volver a cargar el usuario
//                if (changed) {
//                    passPopUp.dismiss();
//                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tancar el PopUp
                passPopUp.dismiss();
            }
        });

    }
*/
    private void updateUser(User usuari) {
        user = APIUtils.update_user(usuari);

    }
}
