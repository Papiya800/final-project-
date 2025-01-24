package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegiLoginActivity extends AppCompatActivity {

    private Context context;
    private boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi_login);

        TextView tv_sign_in = findViewById(R.id.tv_sign_in);
        EditText et_user_phn_num = findViewById(R.id.et_user_phn_num);
        EditText et_user_password = findViewById(R.id.et_user_password);
        Button bt_Login_Button = findViewById(R.id.bt_Login_Button);
        Button bt_Register_Button = findViewById(R.id.bt_Register_Button);


        bt_Login_Button.setOnClickListener(v -> {

            String user_phone = et_user_phn_num.getText().toString();
            String user_password = et_user_password.getText().toString();

            if(user_phone.isEmpty()||user_password.isEmpty())
            {
                Toast.makeText(RegiLoginActivity.this, "Phone number or password is missing!", Toast.LENGTH_SHORT).show();
            }


            else
            {

                //codition when admin login he go admin home page
                if(user_phone.equals("01734874442") && user_password.equals("lisa123"))
                {
                    Intent intent = new Intent(RegiLoginActivity.this,AdminHomeActivity.class);
                    startActivity(intent);

                }

                //or he is normal user
                else{

                    AdminDatabaseHelper db = new AdminDatabaseHelper(context);
                    db.insertAdmin("Papiya Jaman Lisa", "papiyalisa9@gmail.com", "lisa123");

                    if(result)
                    {
                        Toast.makeText(RegiLoginActivity.this, "Sign In Successful!", Toast.LENGTH_SHORT).show();


                    }
                    else
                    {
                        Toast.makeText(RegiLoginActivity.this, "Invalid phone number or password!", Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });







        bt_Register_Button.setOnClickListener(v -> {
            Toast.makeText(RegiLoginActivity.this, "Click for Registration!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegiLoginActivity.this,RegisterActivity.class);
            startActivity(intent);

        });


    }
}