package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButtonToggleGroup;

public class MainActivity extends AppCompatActivity {

    Button addition,subtraction,multiblication;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primarry)));
         getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.primarry));
         getWindow().setNavigationBarColor(ContextCompat .getColor(MainActivity.this,R.color.primarry));

         addition = findViewById(R.id.addition);
         subtraction = findViewById(R.id.subtraction);
         multiblication = findViewById(R.id.multiblication);

         addition.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this,Game.class);
                 startActivity(intent);
                 finish();
             }
         });
         subtraction.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent intent = new Intent(MainActivity.this, com.example.mathgame.subtraction.class);
                 startActivity(intent);
                 finish();

             }
         });
    }
}