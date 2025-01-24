package com.example.finalproject;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        TextView tv_sign_up = findViewById(R.id.tv_sign_up);
        EditText et_name = findViewById(R.id.et_name);
        EditText et_Give_password = findViewById(R.id.et_Give_password);
        EditText et_confirm_password = findViewById(R.id.et_Confirm_password);
        EditText et_user_phone_num = findViewById(R.id.et_user_phone);
        Button bt_sign_up_Button = findViewById(R.id.bt_sign_up_Button);
        Button bt_sign_up_login_Button = findViewById(R.id.bt_sign_up_login_Button);

        bt_sign_up_Button.setOnClickListener(v -> {

            String username = et_name.getText().toString().trim();
            String give_password = et_Give_password.getText().toString().trim();
            String confirm_password = et_confirm_password.getText().toString().trim();
            String user_phone_num = et_user_phone_num.getText().toString().trim();

            if (username.isEmpty() || give_password.isEmpty() || confirm_password.isEmpty() || user_phone_num.isEmpty())
            {
                Toast.makeText(RegisterActivity.this, "All fields must be filled out!", Toast.LENGTH_SHORT).show();
            }
            else if (!give_password.equals(confirm_password))
            {
                Toast.makeText(RegisterActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            }
            else {
                AdminDatabaseHelper dbHelper = new AdminDatabaseHelper(RegisterActivity.this);

                if (dbHelper.onCreate(user_phone_num))
                {
                    Toast.makeText(RegisterActivity.this, "Try another phone number, User already exists!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean isInsert = dbHelper.insertUser(username, give_password, user_phone_num);

                    if (isInsert)
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, RegiLoginActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        bt_sign_up_login_Button.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, RegiLoginActivity.class);
            startActivity(intent);
        });


    }
}