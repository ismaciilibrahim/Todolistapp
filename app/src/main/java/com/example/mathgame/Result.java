package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView result;
    Button playAgain,exit;
    int container =0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primarry)));
        getWindow().setStatusBarColor(ContextCompat.getColor(Result.this,R.color.primarry));
        getWindow().setNavigationBarColor(ContextCompat .getColor(Result.this,R.color.primarry));

        result = findViewById(R.id.textviewresult);
        playAgain = findViewById(R.id.buttonplay);
        exit = findViewById(R.id.buttonexit);

        Intent intent = getIntent();
        container = intent.getIntExtra("score",0);
        String userscore = String.valueOf(container);
        result.setText("Your score is: "+userscore);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Result.this,MainActivity.class);
                startActivity(intent);
                //xir pagekan markii uu furmo kas
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}