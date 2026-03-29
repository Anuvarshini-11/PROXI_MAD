package com.muruga.proxistudy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button createSessionBtn, viewSessionsBtn, feedbackBtn, logoutBtn;
    private TextView welcomeText;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userEmail = getIntent().getStringExtra("userEmail");

        welcomeText = findViewById(R.id.welcomeText);
        createSessionBtn = findViewById(R.id.createSessionBtn);
        viewSessionsBtn = findViewById(R.id.viewSessionsBtn);
        feedbackBtn = findViewById(R.id.feedbackBtn);
        logoutBtn = findViewById(R.id.logoutBtn);

        welcomeText.setText("Welcome, " + userEmail);

        createSessionBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CreateSessionActivity.class);
            startActivity(intent);
        });

        viewSessionsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ViewSessionsActivity.class);
            startActivity(intent);
        });

        feedbackBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FeedbackActivity.class);
            startActivity(intent);
        });

        logoutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
