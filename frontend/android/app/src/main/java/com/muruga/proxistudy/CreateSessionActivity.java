package com.muruga.proxistudy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateSessionActivity extends AppCompatActivity {

    private EditText sessionNameInput, subjectInput, locationInput, maxParticipantsInput;
    private Button submitBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        sessionNameInput = findViewById(R.id.sessionNameInput);
        subjectInput = findViewById(R.id.subjectInput);
        locationInput = findViewById(R.id.locationInput);
        maxParticipantsInput = findViewById(R.id.maxParticipantsInput);
        submitBtn = findViewById(R.id.submitBtn);
        backBtn = findViewById(R.id.backBtn);

        submitBtn.setOnClickListener(v -> {
            String name = sessionNameInput.getText().toString().trim();
            String subject = subjectInput.getText().toString().trim();
            String location = locationInput.getText().toString().trim();
            String maxParticipants = maxParticipantsInput.getText().toString().trim();

            if (name.isEmpty() || subject.isEmpty() || location.isEmpty() || maxParticipants.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Create session and store in memory (handled by SessionManager)
                SessionManager.createSession(name, subject, location, Integer.parseInt(maxParticipants));

                Toast.makeText(this, "Session created successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        backBtn.setOnClickListener(v -> finish());
    }
}
