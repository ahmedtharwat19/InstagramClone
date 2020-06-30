package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.File;
import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEnterEmail, edtUsername, edtEnterPassword;
    private Button btnSignUp, btnLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        edtEnterEmail = findViewById(R.id.edtEnterEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtEnterPassword = findViewById(R.id.edtEnterPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnSignUp:
                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEnterEmail.getText().toString());
                appUser.setUsername(edtUsername.getText().toString());
                appUser.setPassword(edtEnterPassword.getText().toString());

                Context context;
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing Up" + edtUsername.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up" ,
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
                        } else {
                            FancyToast.makeText(SignUp.this,"There was an error: " + e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR,true).show();
                        }
                        progressDialog.dismiss();
                    }
                });
                break;
            case R.id.btnLogIn:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                break;
        }


    }
}