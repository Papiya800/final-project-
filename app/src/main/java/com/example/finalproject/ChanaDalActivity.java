package com.example.finalproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChanaDalActivity extends AppCompatActivity {

    private TextView txtIngredientsLabel;
    private TextView txtIngredients;
    private TextView txtInstructionsLabel;
    private TextView txtInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chana_dal); // Link the XML layout

        // Initialize the TextViews
        txtIngredientsLabel = findViewById(R.id.txt_ingredients_label);
        txtIngredients = findViewById(R.id.txt_ingredients);
        txtInstructionsLabel = findViewById(R.id.txt_instructions_label);
        txtInstructions = findViewById(R.id.txt_instructions);

        // Set the ingredients and instructions text
        String ingredients = "Chana Dal (Split Chickpeas) - 1 cup\nWater - 3½ cups\nTurmeric Powder - ½ tsp\nSalt - ½ tsp + ¼ tsp + to taste\n\nFor Tarka:\nCooking Oil - 2½ tbsp\nCumin Seeds - 1 tsp\nCinnamon Sticks (small) - 2\nCloves - 3\nBlack Peppercorns - 5-6\nBay Leaf - 1\nGarlic (chopped) - 2 tsp\nGinger (chopped) - 1 tsp\nGreen Chilies (finely chopped) - 2-3\nOnion (chopped, white or red) - 1 cup\nTomatoes (chopped) - 2 small\nRed Chili Powder - ½ tsp\nCoriander Powder - 1 tsp\nRoasted Cumin Powder - ½ tsp\nSpinach (chopped) - 100 gm\nGaram Masala - ¼ tsp\nKasuri Methi (Dried Fenugreek Leaves) - ½ tsp\nGhee - 1 tbsp\nLemon or Lime Juice - 1 tsp\n\nFor Garnish:\nGinger (sliced, for garnish)\nFresh Coriander (chopped, for garnish)\nGreen Chili (for garnish)";
        String instructions = "1. Soak the Chana Dal: Soak 1 cup of chana dal in water for over an hour.\n\n2. Cook the Dal: Drain the dal and transfer it to a pan. Add 3½ cups of water, ½ tsp of turmeric, and ½ tsp of salt. Bring to a simmer, then lower the heat and cook until the dal is tender.\n\n3. Prepare the Tarka: Heat 2½ tbsp of cooking oil in a pan. Once hot, add 1 tsp of cumin seeds, 2 small cinnamon sticks, 3 cloves, 5-6 black peppercorns, and 1 bay leaf. Stir for 30-40 seconds until fragrant.\n\n4. Add Garlic and Ginger: Add 2 tsp of chopped garlic and 1 tsp of chopped ginger to the pan. Fry until the raw smell disappears.\n\n5. Add Onions and Chilies: Incorporate 2-3 finely chopped green chilies and 1 cup of chopped onion. Season with ¼ tsp of salt. Fry until the onions turn light brown.\n\n6. Add Tomatoes and Spices: Add 2 small chopped tomatoes, ½ tsp of salt (to taste), ½ tsp of red chili powder, ½ tsp of turmeric, 1 tsp of coriander powder, and ½ tsp of roasted cumin powder. Sauté until the oil separates from the mixture, about 2-3 minutes. Add a splash of water if needed to prevent sticking.\n\n7. Mix in Spinach: Add 100 gm of chopped spinach and stir well for 2-3 minutes.\n\n8. Combine with Cooked Dal: Add the cooked dal to the mixture. Pour in 1 cup of water, ¼ tsp of garam masala, ½ tsp of kasuri methi, and 1 tbsp of ghee. Mix everything well.\n\n9. Simmer: Let the dal simmer on low heat for 3-4 minutes.\n\n10. Final Touches: Turn off the heat. Stir in 1 tsp of lemon or lime juice to taste.\n\n11. Garnish and Serve: Garnish with sliced ginger, chopped coriander, and green chili. Serve hot with porota, roti, or rice.\n\nEnjoy your delicious Dal Palak, a perfect blend of nutrition and flavor!";

        // Set the ingredients and instructions text to the TextViews
        txtIngredients.setText(ingredients);
        txtInstructions.setText(instructions);
    }
}
