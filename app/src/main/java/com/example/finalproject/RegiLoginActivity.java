package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegiLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi_login);

        // Initialize views
        TextView tv_sign_in = findViewById(R.id.tv_sign_in);
        EditText et_user_email = findViewById(R.id.et_user_phn_num); // Renamed to reflect email field
        EditText et_user_password = findViewById(R.id.et_user_password);
        Button bt_Login_Button = findViewById(R.id.bt_Login_Button);
        Button bt_Register_Button = findViewById(R.id.bt_Register_Button);

        bt_Login_Button.setOnClickListener(v -> {
            String userEmailOrPhone = et_user_email.getText().toString().trim();
            String userPassword = et_user_password.getText().toString().trim();

            // Check if inputs are empty
            if (userEmailOrPhone.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(RegiLoginActivity.this, "Email/Phone or password is missing!", Toast.LENGTH_SHORT).show();
            } else {
                // Check if admin login
                if ((userEmailOrPhone.equals("papiyalisa9@gmail.com") && userPassword.equals("lisa123")) ||
                        (userEmailOrPhone.equals("01734874442") && userPassword.equals("lisa123"))) {
                    // Redirect to Admin Home Activity
                    Intent intent = new Intent(RegiLoginActivity.this, AdminHomeActivity.class);
                    startActivity(intent);
                } else {
                    // Check if normal user login
                    DatabaseHelper databaseHelper = new DatabaseHelper(RegiLoginActivity.this);
                    boolean result = databaseHelper.checkUserByPhone(userEmailOrPhone, userPassword);

                    if (result) {
                        Toast.makeText(RegiLoginActivity.this, "Sign In Successful!", Toast.LENGTH_SHORT).show();
                        // Redirect to Recipe Categories Activity
                        Intent intent = new Intent(RegiLoginActivity.this, RecipeCategoriesActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegiLoginActivity.this, "Invalid phone number or password!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        bt_Register_Button.setOnClickListener(v -> {
            // Redirect to Register Activity (you can implement this activity)
            Intent intent = new Intent(RegiLoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
