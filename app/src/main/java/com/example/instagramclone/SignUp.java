package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        edtEnterPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);

                }
                return false;
            }
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            finish();
            transationToSocialMediaActivity();
        }

    }


    @Override
    public void onClick(View view) {
//        dismissKeyboard(SignUp.this);
        rootLayoutTapped(view);
        switch (view.getId()){
            case R.id.btnSignUp:

                if (edtEnterEmail.getText().toString().equals("") ||
                        edtUsername.getText().toString().equals("") ||
                        edtEnterPassword.getText().toString().equals("")){
                    FancyToast.makeText(SignUp.this, "Email, Username, Password is required!" ,
                            FancyToast.LENGTH_SHORT, FancyToast.INFO,true).show();
                } else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEnterEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtEnterPassword.getText().toString());


                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up" + edtUsername.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                finish();
                                transationToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(SignUp.this, "There was an error: " + e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogIn:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }


    }
/*    public void dismissKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus())
            imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }*/


    public void rootLayoutTapped(View view){
        try {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void transationToSocialMediaActivity(){
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
    }

}
