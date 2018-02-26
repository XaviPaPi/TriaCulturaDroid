package com.example.xavi.triaculturadroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginTriaCultura extends AppCompatActivity {

    private Button btn_Acces;
    private AutoCompleteTextView mUserView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_tria_cultura);

        mUserView = (AutoCompleteTextView) findViewById(R.id.text_usuari);
        mUserView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    verificarUsuariBuit();
                }
            }
        });

        btn_Acces = (Button) findViewById(R.id.tcLogin_button);
        btn_Acces.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verificarUsuariAndPass()) {
                    Intent intent = new Intent(getApplication(), TabbetsActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplication(), R.string.errorPassOrUsrInvalid, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean verificarUsuariAndPass(){


        return true;
    }

    private void verificarUsuariBuit(){
        if(mUserView.getText().toString().isEmpty()){
            Toast.makeText(this, R.string.userField, Toast.LENGTH_SHORT).show();
        }
    }
}

