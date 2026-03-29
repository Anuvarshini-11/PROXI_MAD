package com.muruga.proxistudy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ViewSessionsActivity extends AppCompatActivity {

    private LinearLayout sessionsContainer;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sessions);

        sessionsContainer = findViewById(R.id.sessionsContainer);
        backBtn = findViewById(R.id.backBtn);

        displaySessions();

        backBtn.setOnClickListener(v -> finish());
    }

    private void displaySessions() {
        sessionsContainer.removeAllViews();

        if (SessionManager.sessions.isEmpty()) {
            TextView emptyText = new TextView(this);
            emptyText.setText("No sessions available");
            emptyText.setTextSize(16);
            emptyText.setPadding(16, 16, 16, 16);
            sessionsContainer.addView(emptyText);
            return;
        }

        for (int i = 0; i < SessionManager.sessions.size(); i++) {
            Session session = SessionManager.sessions.get(i);

            LinearLayout sessionLayout = new LinearLayout(this);
            sessionLayout.setOrientation(LinearLayout.VERTICAL);
            sessionLayout.setPadding(16, 12, 16, 12);
            sessionLayout.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 8, 0, 8);
            sessionLayout.setLayoutParams(layoutParams);

            TextView sessionName = new TextView(this);
            sessionName.setText("Session: " + session.name);
            sessionName.setTextSize(16);
            sessionName.setTextColor(0xFFFFFFFF);

            TextView sessionSubject = new TextView(this);
            sessionSubject.setText("Subject: " + session.subject);
            sessionSubject.setTextSize(14);
            sessionSubject.setTextColor(0xFFCCCCCC);

            TextView sessionLocation = new TextView(this);
            sessionLocation.setText("Location: " + session.location);
            sessionLocation.setTextSize(14);
            sessionLocation.setTextColor(0xFFCCCCCC);

            TextView sessionSlots = new TextView(this);
            int availableSlots = session.maxParticipants - session.participants;
            sessionSlots.setText("Available Slots: " + availableSlots + "/" + session.maxParticipants);
            sessionSlots.setTextSize(14);
            sessionSlots.setTextColor(0xFFCCCCCC);

            Button joinBtn = new Button(this);
            joinBtn.setText(availableSlots > 0 ? "Join Session" : "Session Full");
            joinBtn.setEnabled(availableSlots > 0);
            int finalI = i;
            joinBtn.setOnClickListener(v -> {
                if (SessionManager.sessions.get(finalI).participants < SessionManager.sessions.get(finalI).maxParticipants) {
                    SessionManager.sessions.get(finalI).participants++;
                    Toast.makeText(ViewSessionsActivity.this, "Joined session successfully", Toast.LENGTH_SHORT).show();
                    displaySessions();
                } else {
                    Toast.makeText(ViewSessionsActivity.this, "Session is full", Toast.LENGTH_SHORT).show();
                }
            });

            sessionLayout.addView(sessionName);
            sessionLayout.addView(sessionSubject);
            sessionLayout.addView(sessionLocation);
            sessionLayout.addView(sessionSlots);
            sessionLayout.addView(joinBtn);

            sessionsContainer.addView(sessionLayout);
        }
    }
}
