package com.example.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private TextView scoreText;
    private Button newGameButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        scoreText = findViewById(R.id.scoreText);
        newGameButton = findViewById(R.id.newGameButton);
        exitButton = findViewById(R.id.exitButton);

        int score = getIntent().getIntExtra("score", 0);

        scoreText.setText("Score: " + score);

        newGameButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity2.this,
                    MainActivity.class
            );

            startActivity(intent);

            finish();
        });

        exitButton.setOnClickListener(v -> {

            finishAffinity();
        });
    }
}