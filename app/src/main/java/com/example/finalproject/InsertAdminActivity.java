package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertAdminActivity extends AppCompatActivity {

    private EditText etAdminName, etAdminEmail, etAdminPassword;
    private Button btnSaveAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_admin);

        // Initialize UI elements
        etAdminName = findViewById(R.id.et_admin_name);
        etAdminEmail = findViewById(R.id.et_admin_email);
        etAdminPassword = findViewById(R.id.et_admin_password);
        btnSaveAdmin = findViewById(R.id.btn_save_admin);


        btnSaveAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String adminName = etAdminName.getText().toString().trim();
                String adminEmail = etAdminEmail.getText().toString().trim();
                String adminPassword = etAdminPassword.getText().toString().trim();


                if (adminName.isEmpty() || adminEmail.isEmpty() || adminPassword.isEmpty()) {
                    Toast.makeText(InsertAdminActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(InsertAdminActivity.this, "Admin Saved:\n" +
                            "Name: " + adminName + "\nEmail: " + adminEmail, Toast.LENGTH_LONG).show();

                    // Clear input fields after saving
                    etAdminName.setText("Papiya Jaman Lisa");
                    etAdminEmail.setText("papiyalisa9@gmail.com");
                    etAdminPassword.setText("lisa123");
                }
            }
        });
    }
}
