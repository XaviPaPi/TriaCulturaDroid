package com.example.xavi.triaculturadroid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private AutoCompleteTextView mUserView;
    private EditText mPasswordView;
    User retrieved_user;
    CheckBox cbRememberMe;

    public static final String PREFS_NAME = "SPFile";
    String nom;
    SharedPreferences config;
    SharedPreferences.Editor editor;
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


                    log_in();

                } else {
                    Toast.makeText(LoginTriaCultura.this, "Els camps estan buits", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void log_in() {
        boolean exists = verificarUsuariAndPass(mUserView.getText().toString());

        if (exists) {
            Intent intent = new Intent(getApplication(), TabbetsActivity.class);
//            userTransfer usuari= new userTransfer(retrieved_user);
            intent.putExtra("Usuari", "" + retrieved_user.getDni());
            sharedPreferences();
            startActivity(intent);
        } else {
            Toast.makeText(getApplication(), R.string.errorPassOrUsrInvalid, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verificarUsuariAndPass(final String user_dni) {

        progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Carregant...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        retrieved_user = APIUtils.get_user_by_dni(user_dni);
        progressDialog.dismiss();
        if (retrieved_user != null && retrieved_user.getDni() != null) {
            boolean correct = retrieved_user.getPassword().equals(mPasswordView.getText().toString());
            return correct;
        }
        return false;
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

    private void sharedPreferences() {

        //obtenir fitxer PREFS_NAME al SharedPreferences (SP)
        config = getSharedPreferences(PREFS_NAME, 0);
        //creem l'obejcet editor per poder fer canvis al SP
        editor = config.edit();
        String etName = mUserView.getText().toString();
        editor.putString("name", etName);
        if (cbRememberMe.isChecked()) {
            editor.putBoolean("checked", true);
        } else {
            editor.putBoolean("checked", false);
        }
        editor.commit();
    }
}

