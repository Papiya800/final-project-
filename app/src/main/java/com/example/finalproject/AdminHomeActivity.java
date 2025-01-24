package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminHomeActivity extends AppCompatActivity {

    private Button btnInsertAdmin, btnViewAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home_activity);

        // Buttons Initialization
        btnInsertAdmin = findViewById(R.id.btn_insert_admin);
        btnViewAdmin = findViewById(R.id.btn_view_admin);

        // Set Click Listeners
        btnInsertAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Insert Admin Activity
                Intent intent = new Intent(AdminHomeActivity.this, InsertAdminActivity.class);
                startActivity(intent);
            }
        });

        btnViewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to View/Delete/Update Admin Activity
                Intent intent = new Intent(AdminHomeActivity.this, ViewAdminActivity.class);
                startActivity(intent);
            }
        });
    }
}
