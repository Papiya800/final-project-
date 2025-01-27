package com.example.finalproject;


import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChickenMughlaiPorotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_mughlai_porota);

        // Fetching the TextView for displaying instructions
        TextView txtInstructions = findViewById(R.id.txt_instructions);

        // Setting the recipe text dynamically (optional if hardcoded in XML)
        String instructions = "Ingredients:\n\n"
                + "Filling\n\n"
                + "2 tbsp cooking oil\n"
                + "1 medium onion\n"
                + "2 small cinnamon sticks\n"
                + "\u00bd tsp garlic paste\n"
                + "1 tsp ginger paste\n"
                + "500 g minced chicken\n"
                + "1 tsp salt\n"
                + "\u00bd tsp turmeric\n"
                + "\u00bd tsp roasted cumin\n"
                + "\u00bd tsp red chili\n"
                + "2 tbsp lime juice\n"
                + "1 egg\n"
                + "1 tsp onion\n"
                + "1 tsp fresh coriander\n"
                + "1 tsp green chili\n\n"
                + "Porota\n\n"
                + "3 cups ap flour\n"
                + "\u00bd tsp salt\n"
                + "2 tbsp cooking oil\n"
                + "1 cup + 2 tbsp water\n\n"
                + "Instructions:\n\n"
                + "1. Start off by adding cooking oil in a large pot\n"
                + "2. Stir in chopped onion, cinnamon sticks, garlic, and ginger paste\n"
                + "3. Add 500 grams of minced chicken into the pot\n"
                + "4. Mix in salt, turmeric, roasted cumin, and red chili\n"
                + "5. Cook the minced chicken for 7 to 8 minutes\n"
                + "6. Pour in lime juice and remove the chicken from heat and set aside\n"
                + "7. Prepare the porota by adding ap flour in a large clear bowl\n"
                + "8. Mix in salt, cooking oil, and water\n"
                + "9. Knead a soft dough\n"
                + "10. Coat the dough in cooking oil and set aside for it to rest 15 minutes\n"
                + "11. Cut dough into 6 even pieces\n"
                + "12. Roll out the dough\n"
                + "13. Crack an egg and whisk in chopped onion, fresh coriander, and green chilies\n"
                + "14. Pour the whisked egg onto the porota and spread evenly\n"
                + "15. Place the cooked chicken on top of the egg spread\n"
                + "16. Fold the porota and fry in a pan with a drizzle of cooking oil for 6 to 7 minutes on low-medium heat\n"
                + "17. Remove the porota from heat and cut into small square pieces before serving\n"
                + "18. Ready to enjoy!";

        txtInstructions.setText(instructions);
    }
}
