package com.example.xavi.triaculturadroid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xavi.triaculturadroid.Data.Model.User;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;


public class LoginTriaCultura extends AppCompatActivity {

    private Button btn_Acces;
    public static AutoCompleteTextView mUserView;
    public static EditText mPasswordView;
    public static User retrieved_user;
    public static CheckBox cbRememberMe;
    boolean exists;
    SharedPreferences.Editor editor;

    SharedPreferences config;
    public static final String PREFS_NAME = "SPFile";
    String nom;
    ProgressDialog progressDialog;
    boolean correct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_tria_cultura);
        APIUtils.init_service();

        mUserView = (AutoCompleteTextView) findViewById(R.id.text_usuari);
        cbRememberMe = (CheckBox) findViewById(R.id.ltc_cb_rememberMe);

        config = getSharedPreferences(PREFS_NAME, 0);
        nom = config.getString("name", null);

        boolean cheked = config.getBoolean("checked", false);
        if (cheked) {
            cbRememberMe.setChecked(true);
        }

        mUserView.setText(nom);

        config = getSharedPreferences(PREFS_NAME, 0);
        nom = config.getString("name", null);
        mUserView.setText(nom);
        mUserView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    verificarUsuariBuit();
                }
            }
        });
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    verificarPasswordBuit();
                }
            }
        });

        btn_Acces = (Button) findViewById(R.id.tcLogin_button);
        btn_Acces.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mUserView.getText().toString().isEmpty() && !mPasswordView.getText().toString().isEmpty()) {
                    String user_dni = mUserView.getText().toString();
                    progressDialog = new ProgressDialog(LoginTriaCultura.this);
                    progressDialog.setIcon(R.mipmap.ic_launcher);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Carregant...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    UserSearch retrieve = new UserSearch(user_dni, progressDialog, view.getContext());
                    retrieve.start();
                } else {
                    Toast.makeText(LoginTriaCultura.this, "Els camps estan buits", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verificarUsuariBuit() {
        if (mUserView.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.userField, Toast.LENGTH_SHORT).show();
        }
    }

    private void verificarPasswordBuit() {
        if (mPasswordView.getText().toString().isEmpty()) {
            Toast.makeText(this, "El camp del password és buit", Toast.LENGTH_SHORT).show();
        }
    }

}

