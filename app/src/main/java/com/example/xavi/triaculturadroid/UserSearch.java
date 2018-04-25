package com.example.xavi.triaculturadroid;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.xavi.triaculturadroid.Data.Model.User;
import com.example.xavi.triaculturadroid.Data.Remote.APIUtils;

import static com.example.xavi.triaculturadroid.LoginTriaCultura.PREFS_NAME;


/**
 * Created by demian ${EMAIL}
 * 04/18/18.
 */

public class UserSearch extends Thread {
    private User user;
    private String userdni;
    public static ProgressDialog pd;
    public boolean exists = false;
    private Context context;
    SharedPreferences config;
    SharedPreferences.Editor editor;

    public UserSearch(String userdni, final ProgressDialog progd, final Context context) {
        this.pd = progd;
        this.context =context;
        this.userdni = userdni;

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (user != null && user.getDni() != null) {
                    exists = user.getPassword().equals(LoginTriaCultura.mPasswordView.getText().toString());
                    if (exists) {
                        log_in();
                    } else {
                        Toast.makeText(context, R.string.errorPassOrUsrInvalid, Toast.LENGTH_SHORT).show();
                        tanca_dialog();
                    }
                }
            }
        };
    }

    @Override
    public void run() {
        user = APIUtils.get_user_by_dni(userdni);
        handler.sendEmptyMessage(0);
    }
    public void log_in() {

        Intent intent = new Intent(context, TabbetsActivity.class);
        intent.putExtra("Usuari", "" + user.getDni());
        sharedPreferences();
        context.startActivity(intent);
    }

    private Handler handler;

    public static void tanca_dialog() {
        pd.dismiss();
    }

    public User getUser() {
        return user;
    }
    private void sharedPreferences() {

        //obtenir fitxer PREFS_NAME al SharedPreferences (SP)
        config = context.getSharedPreferences(PREFS_NAME, 0);
        //creem l'obejcet editor per poder fer canvis al SP
        editor = config.edit();
        String etName = LoginTriaCultura.mUserView.getText().toString();
        editor.putString("name", etName);
        if (LoginTriaCultura.cbRememberMe.isChecked()) {
            editor.putBoolean("checked", true);
        } else {
            editor.putBoolean("checked", false);
        }
        editor.commit();
    }
}

