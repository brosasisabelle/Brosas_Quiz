package com.isabelle.brosas_quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText et_username;
    EditText et_password;
    Button btn_remember;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_remember = (Button) findViewById(R.id.btn_remember);
        btn_login = (Button) findViewById(R.id.btn_login);

        et_username.setOnKeyListener(new EditText.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String user = preferences.getString("username","");
                String pass = preferences.getString("password","");

                String sUsername = et_username.getText().toString();

                if(!user.isEmpty()) {
                    if (sUsername.equals(user)) {
                        et_password.setText(pass);
                        et_password.setBackgroundColor(Color.YELLOW);
                    }
                    else if (!(sUsername.equals(user))){
                        et_password.setText("");
                        et_password.setBackgroundColor(Color.TRANSPARENT);
                    }
                }

                return false;
            }
        });

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String user = preferences.getString("username","");
                    String pass = preferences.getString("password","");

                    String sUsername = et_username.getText().toString();

                    if(!user.isEmpty()) {
                        if (sUsername.equals(user)) {
                            et_password.setText(pass);
                            et_password.setBackgroundColor(Color.YELLOW);
                        }
                        else if (!(sUsername.equals(user))){
                            et_password.setText("");
                            et_password.setBackgroundColor(Color.TRANSPARENT);
                        }
                    }

                }
            }
        });
    }

    public void rememberMe (View view){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", et_username.getText().toString());
        editor.putString("password", et_password.getText().toString());
        editor.commit();
        Toast.makeText(this, "Preference Saved!", Toast.LENGTH_SHORT).show();

    }

    public void login (View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }



}
