package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUserNameSignUp, edtPasswordSignUp, edtUserNameLogin, edtPasswordLogin;
    private Button btnSignUp, btnLogin;
    public String userNameLogged;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, "you are Sign up successfully " + appUser.getUsername(),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            Intent intent = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage() ,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if ( user != null && e == null) {
                            FancyToast.makeText(SignUpLoginActivity.this, user.getUsername() + " is logged in successfully", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            //edtUserNameLogin.getText().toString()
                            userNameLogged = user.getUsername();
                            Intent intent = new Intent(SignUpLoginActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                        } else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage() ,FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });

    }
}
