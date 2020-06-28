package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("T5Y6mkE6NhLMGu18JJW9Yz4SNmAPxEEsj22ZqraR")
                // if defined
                .clientKey("Mku0rU1W3VlWFC4ZcXpZVbbEKYjf0tZpSCzugpHm")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
