package com.example.guessthenumber;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private TextView scoreText;
    private TextView highScoreText;
    private Button newGameButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        scoreText = findViewById(R.id.scoreText);
        highScoreText = findViewById(R.id.highScoreText);
        newGameButton = findViewById(R.id.newGameButton);
        exitButton = findViewById(R.id.exitButton);

        int score = getIntent().getIntExtra("score", 0);
        String name = getIntent().getStringExtra("name");

        scoreText.setText("Score: " + score);

        SharedPreferences preferences =
                getSharedPreferences("GamePrefs", MODE_PRIVATE);

        int highScore = preferences.getInt("highScore", 0);
        String highScoreName =
                preferences.getString("highScoreName", "");

        if (score > highScore) {

            highScore = score;
            highScoreName = name;

            preferences.edit()
                    .putInt("highScore", highScore)
                    .putString("highScoreName", highScoreName)
                    .apply();
        }

        highScoreText.setText(
                "High Score: " + highScore + "\n" +
                        highScoreName
        );

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