package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  ImageButton btnrecipe = findViewById(R.id.imgrecipe);

        ImageButton buttonRecipe = findViewById(R.id.buttonRecipe);
        buttonRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecipeSearchActivity.class);
                startActivity(intent);
            }
        });




//        Button recipeSearch = (Button)findViewById(R.id.recipeSearch);
//        recipeSearch.setOnClickListener( clk -> {
//            Intent goToRecipeSearchActivity = new Intent(MainActivity.this, RecipeSearchActivity.class);
//            startActivity(goToRecipeSearchActivity);
//        });
    }
}
