package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        // Initialize views
        EditText et_name = findViewById(R.id.et_name);
        EditText et_user_phone = findViewById(R.id.et_user_phone);
        EditText et_Give_password = findViewById(R.id.et_Give_password);
        EditText et_Confirm_password = findViewById(R.id.et_Confirm_password); // Added confirm password field
        Button bt_sign_up_Button = findViewById(R.id.bt_sign_up_Button);

        bt_sign_up_Button.setOnClickListener(v -> {
            String userName = et_name.getText().toString().trim();
            String userPhone = et_user_phone.getText().toString().trim();
            String userPassword = et_Give_password.getText().toString().trim();
            String confirmPassword = et_Confirm_password.getText().toString().trim();

            // Check if all fields are filled
            if (userName.isEmpty() || userPhone.isEmpty() || userPassword.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();
            } else if (!userPassword.equals(confirmPassword)) {
                // Check if password and confirm password match
                Toast.makeText(RegisterActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            } else {
                // Save data to the database
                boolean isRegistered = db.registerUser(userName, userPhone, userPassword);
                if (isRegistered) {
                    Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, RegiLoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration Failed! Try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
