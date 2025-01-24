package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BreakfastMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast_menu);

        // TextView (Breakfast Menu)
        TextView txtBreakfastMenu = findViewById(R.id.txt_breakfast_menu);

        // Buttons
        Button btnQuesadilla = findViewById(R.id.btn_quesadilla);
        Button btnChanaDal = findViewById(R.id.btn_chana_dal);
        Button btnChickenMughlaiPorotaRecipe = findViewById(R.id.btn_chicken_mughlai_porota_recipe);

        // Set click listener for the TextView (Breakfast Menu)
        txtBreakfastMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BreakfastMenuActivity.this, "Breakfast Menu clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for Quesadilla button
        btnQuesadilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BreakfastMenuActivity.this, QuesadillaActivity.class);
                startActivity(intent);
            }
        });

//        // Set click listener for Chana Dal button
//        btnChanaDal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BreakfastMenuActivity.this, ChanaDalActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        // Set click listener for Chicken Mughlai Porota Recipe button
//        btnChickenMughlaiPorotaRecipe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BreakfastMenuActivity.this, ChickenMughlaiPorotaActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
