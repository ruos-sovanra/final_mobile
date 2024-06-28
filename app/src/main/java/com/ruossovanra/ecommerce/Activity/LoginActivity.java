package com.ruossovanra.ecommerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ruossovanra.ecommerce.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    // Handle login logic here
                    Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();

                    // Start MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                    // Optionally, if you don't want users to go back to the LoginActivity when they press the back button, call finish()
                    finish();
                }
            }
        });
    }

    private boolean validateInput() {
        if (usernameEditText.getText().toString().isEmpty()) {
            usernameEditText.setError("Username cannot be empty");
            return false;
        }

        if (passwordEditText.getText().toString().isEmpty()) {
            passwordEditText.setError("Password cannot be empty");
            return false;
        }

        return true;
    }
}