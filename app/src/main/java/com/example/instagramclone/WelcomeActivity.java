package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {
    private Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        final TextView txtWelcome = findViewById(R.id.txtWelcome);

        txtWelcome.setText("Welcome!" + ParseUser.getCurrentUser().getUsername());
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                txtWelcome.setText("Please Login to resume");
//                Intent intent = new Intent(WelcomeActivity.this, SignUpLoginActivity.class);
                finish();
            }
        });

    }
}