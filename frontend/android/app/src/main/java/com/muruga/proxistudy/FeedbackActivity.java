package com.muruga.proxistudy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FeedbackActivity extends AppCompatActivity {

    private EditText feedbackInput;
    private Button submitBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedbackInput = findViewById(R.id.feedbackInput);
        submitBtn = findViewById(R.id.submitBtn);
        backBtn = findViewById(R.id.backBtn);

        submitBtn.setOnClickListener(v -> {
            String feedback = feedbackInput.getText().toString().trim();

            if (feedback.isEmpty()) {
                Toast.makeText(this, "Please enter feedback", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();
                feedbackInput.setText("");
                finish();
            }
        });

        backBtn.setOnClickListener(v -> finish());
    }
}
