package com.example.finalproject;



import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RiceRecipeActivity extends AppCompatActivity {

    private TextView txtIngredientsLabel;
    private TextView txtIngredients;
    private TextView txtInstructionsLabel;
    private TextView txtInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_recipe);

        // Initialize Views
        txtIngredientsLabel = findViewById(R.id.txt_ingredients_label);
        txtIngredients = findViewById(R.id.txt_ingredients);
        txtInstructionsLabel = findViewById(R.id.txt_instructions_label);
        txtInstructions = findViewById(R.id.txt_instructions);

        // Set Data (if needed, customize here)
        txtIngredientsLabel.setText("Ingredients");
        txtIngredients.setText("1 tbsp cooking oil\n\n1 tsp garlic paste\n\n1 tsp ginger paste\n\n250g thinly sliced chicken\n\n¼ tsp salt, plus more to taste\n\n¼ tsp pepper, plus more to taste\n\n1 tsp soy sauce\n\nAnother splash of cooking oil\n\n½ cup julienned carrots\n\n½ cup green beans, chopped\n\nPinch of salt\n\n1½ cups shredded cabbage\n\n¼ cup frozen peas\n\n1½ tbsp ghee\n\n1 small cinnamon stick\n\n4 cardamoms\n\n3 cloves\n\n1 bay leaf\n\n¼ cup cashews\n\n1½ tbsp raisins\n\n2 cups cooked rice\n\n1 tbsp white vinegar\n\n1½ tsp sugar\n\nSpring onions, finely chopped for garnish\n\nGreen chilies, finely chopped for garnish");

        txtInstructionsLabel.setText("Instructions");
        txtInstructions.setText("Heat 1 tbsp of cooking oil in a pan. Add the garlic and ginger pastes, stirring quickly in the hot oil.\n\nAdd the chicken slices, ¼ tsp salt, and ¼ tsp pepper. Mix well and cook for 3-4 minutes after adding 1 tsp soy sauce. Once cooked, set the chicken aside.\n\nIn the same pan, add another splash of oil, then toss in the carrots and green beans. Sprinkle with a pinch of salt and cook for 1-2 minutes.\n\nAdd the cabbage and frozen peas, and sauté on high heat for another minute. Set the vegetables aside.\n\nIn the same pan, add the ghee, cinnamon stick, cardamoms, cloves, and bay leaf. Stir until fragrant.\n\nAdd the cashews and raisins, cooking until the raisins plump up.\n\nStir in the cooked rice, white vinegar, sugar, and adjust salt and pepper to taste. Mix thoroughly.\n\nCombine the cooked vegetables and chicken back into the pan. Mix everything well to distribute flavors evenly.\n\nGarnish with finely chopped spring onions and green chilies.");
    }
}
