package com.example.c402.loginpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserHomePage extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
        textView = findViewById(R.id.tvWelcome);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String userName = bundle.getString("KEY_USERNAME");
        textView.setText("Welcome, " + userName);
    }
}
