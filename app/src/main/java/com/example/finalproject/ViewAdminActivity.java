package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewAdminActivity extends AppCompatActivity {

    private ListView adminListView;
    private Button btnUpdateAdmin, btnDeleteAdmin;
    private ArrayList<String> adminList;
    private ArrayAdapter<String> adapter;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_admin);

        // Initializing views
        adminListView = findViewById(R.id.admin_list_view); // Corrected ID
        btnUpdateAdmin = findViewById(R.id.btn_update_admin);
        btnDeleteAdmin = findViewById(R.id.btn_delete_admin);

        // Sample admin list
        adminList = new ArrayList<>();
        adminList.add("Admin 1");
        adminList.add("Admin 2");
        adminList.add("Admin 3");
        adminList.add("Admin 4");

        // Setting up adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, adminList);
        adminListView.setAdapter(adapter);
        adminListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // ListView item selection
        adminListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                Toast.makeText(ViewAdminActivity.this, "Selected: " + adminList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        // Update admin button click
        btnUpdateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    String selectedAdmin = adminList.get(selectedPosition);
                    Toast.makeText(ViewAdminActivity.this, "Update Admin: " + selectedAdmin, Toast.LENGTH_SHORT).show();
                    // Add your update logic here
                } else {
                    Toast.makeText(ViewAdminActivity.this, "Please select an admin to update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Delete admin button click
        btnDeleteAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != -1) {
                    String selectedAdmin = adminList.get(selectedPosition);
                    adminList.remove(selectedPosition);
                    adapter.notifyDataSetChanged();
                    selectedPosition = -1;
                    Toast.makeText(ViewAdminActivity.this, "Deleted Admin: " + selectedAdmin, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ViewAdminActivity.this, "Please select an admin to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
