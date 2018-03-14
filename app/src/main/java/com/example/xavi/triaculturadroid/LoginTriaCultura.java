package com.example.xavi.triaculturadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xavi.triaculturadroid.Data.Model.User;
import com.example.xavi.triaculturadroid.Data.Model.userTransfer;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;


public class LoginTriaCultura extends AppCompatActivity {

    private Button btn_Acces;
    private AutoCompleteTextView mUserView;
    private EditText mPasswordView;
    User retrieved_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_tria_cultura);
        APIUtils.init_service();

        mUserView = (AutoCompleteTextView) findViewById(R.id.text_usuari);
        mUserView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    verificarUsuariBuit();
                    verificarPasswordBuit();
                }
            }
        });
        mPasswordView = findViewById(R.id.password);

        btn_Acces = (Button) findViewById(R.id.tcLogin_button);
        btn_Acces.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                log_in();
            }
        });
    }

    private void log_in() {
        boolean exists = verificarUsuariAndPass(mUserView.getText().toString());
        if (exists) {
            Intent intent = new Intent(getApplication(), TabbetsActivity.class);
            userTransfer usuari= new userTransfer();
            usuari.setId(retrieved_user.getId());
            usuari.setDni(retrieved_user.getDni());
            usuari.setPassword(retrieved_user.getPassword());
            intent.putExtra("Usuari",usuari);
            startActivity(intent);
        } else {
            Toast.makeText(getApplication(), R.string.errorPassOrUsrInvalid, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verificarUsuariAndPass(String user_dni) {
        retrieved_user = APIUtils.get_user_by_dni(user_dni);

        if (retrieved_user != null) {
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

    private void verificarPasswordBuit(){
        if(mPasswordView.getText().toString().isEmpty()){
            Toast.makeText(this, "El camp del password és buit", Toast.LENGTH_SHORT).show();
        }
    }
}

