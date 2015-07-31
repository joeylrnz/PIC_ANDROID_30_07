package com.example.administrador.myapplication.controller;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.model.entities.User;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    private User user;
    EditText editTextUserName,editTextPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName=(EditText)findViewById(R.id.editTextUserName);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);

        bindSignupButton();
        bindLoginButton();
    }

    private void bindSignupButton() {
        buttonLogin = (Button) findViewById(R.id.buttonSignup);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                if (userName.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.fill_all_fields, Toast.LENGTH_LONG).show();
                    return;
                }
                User user = User.registerUser(userName, password);
                if (user != null) {
                    Toast.makeText(getApplicationContext(), String.format(getResources().getString(R.string.signup_ok), user.getUserid()), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.signup_failed, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void bindLoginButton() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                if(userName.equals("")||password.equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.fill_all_fields, Toast.LENGTH_LONG).show();
                    return;
                }
                User user = User.getUser(userName, password);
                if(user != null) {
                    Toast.makeText(getApplicationContext(), String.format(getResources().getString(R.string.login_ok), user.getUserid()), Toast.LENGTH_LONG).show();
                    Intent goToMainActivity = new Intent(LoginActivity.this, ClientListActivity.class);
                    startActivity(goToMainActivity);
                }
                else {
                    Toast.makeText(getApplicationContext(), R.string.login_failed, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
