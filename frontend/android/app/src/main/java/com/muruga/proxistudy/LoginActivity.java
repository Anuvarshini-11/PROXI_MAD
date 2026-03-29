package com.muruga.proxistudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText nameInput, emailInput, passwordInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> handleRegisterAndLogin());
    }

    private void handleRegisterAndLogin() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show loading state
        registerButton.setEnabled(false);
        registerButton.setText("Registering...");

        // Run on background thread
        new Thread(() -> {
            ApiClient.ApiResponse response = ApiClient.register(name, email, password);
            
            runOnUiThread(() -> {
                registerButton.setEnabled(true);
                registerButton.setText("Register & Login");

                if (response.error != null) {
                    Toast.makeText(LoginActivity.this, "Error: " + response.error, Toast.LENGTH_SHORT).show();
                } else if (response.statusCode.equals("201") || response.statusCode.equals("200")) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response.body);
                        String token = jsonResponse.optString("token", "");
                        String userId = jsonResponse.optString("_id", "");
                        
                        // Save token and user info
                        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                        prefs.edit()
                                .putString("userEmail", email)
                                .putString("userName", name)
                                .putString("token", token)
                                .putString("userId", userId)
                                .apply();

                        Toast.makeText(LoginActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        
                        // Navigate to Home
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("userEmail", email);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Error parsing response: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        JSONObject errorObj = new JSONObject(response.body);
                        String errorMsg = errorObj.optString("message", "Registration failed");
                        Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Registration failed (HTTP " + response.statusCode + ")", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }).start();
    }
}

