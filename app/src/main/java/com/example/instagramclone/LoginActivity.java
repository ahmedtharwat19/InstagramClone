package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtLoginEmail, edtLoginPassword;
    Button btnLoginActivity, btnSignupActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignupActivity = findViewById(R.id.btnSignupLoginActivity);

        btnSignupActivity.setOnClickListener(this);
        btnLoginActivity.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginActivity:
                ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if( user != null && e == null) {
                            FancyToast.makeText(LoginActivity.this, user.getUsername() + " is Logged In successfully",
                                    FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        } else {
                            FancyToast.makeText(LoginActivity.this, "There was an error: " + e.getMessage(),
                                    FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });

                break;
            case R.id.btnSignupLoginActivity:
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
                break;
        }
    }
}