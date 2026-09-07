package com.example.guessthenumber;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private TextView scoreText;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // فتح صفحة الـ Score
        setContentView(R.layout.activity_main2);

        // ربط العناصر
        scoreText = findViewById(R.id.scoreText);
        backButton = findViewById(R.id.backButton);

        // استقبال الـ score من MainActivity
        int score = getIntent().getIntExtra("score", 0);

        // عرض الـ score
        scoreText.setText("Score: " + score);


        // =========================
        // BACK BUTTON
        // =========================

        backButton.setOnClickListener(v -> {

            finish();
        });
    }
}
