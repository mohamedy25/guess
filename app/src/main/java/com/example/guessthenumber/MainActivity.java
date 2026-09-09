package com.example.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView t1;
    private TextView t2;
    private TextView timerText;

    private Button bt;
    private EditText et, name;

    private int num;
    private int score = 0;
    private int count = 0;

    private CountDownTimer timer;
    private boolean gameStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);
        timerText = findViewById(R.id.timerText);
        name = findViewById(R.id.name);
        bt = findViewById(R.id.button);
        et = findViewById(R.id.editTextText);

        num = (int) (Math.random() * 21);

        et.setOnFocusChangeListener((v, hasFocus) -> {

            if (hasFocus && !gameStarted) {

                String playerName = name.getText().toString().trim();

                if (playerName.isEmpty()) {
                    name.setError("Please enter your name");
                    name.requestFocus();
                    return;
                }

                gameStarted = true;
                name.setEnabled(false);
                startTimer();
            }
        });

        bt.setOnClickListener(v -> {

            if (!gameStarted) {

                String playerName = name.getText().toString().trim();

                if (playerName.isEmpty()) {
                    name.setError("Please enter your name");
                    name.requestFocus();
                    return;
                }

                gameStarted = true;
                name.setEnabled(false);
                startTimer();
            }

            String input = et.getText().toString();

            if (input.isEmpty()) {
                t2.setText("Enter a number!");
                return;
            }

            int guess;

            try {
                guess = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                t2.setText("Enter a valid number!");
                return;
            }

            if (guess < 0 || guess > 20) {
                t2.setText("Enter a number from 0 to 20!");
                et.setText("");
                return;
            }

            count++;

            if (guess == num) {

                score += 2;

                t2.setText("Correct! You won 2 points");

                num = (int) (Math.random() * 21);

                count = 0;

                startTimer();

            } else if (guess < num) {

                t2.setText("Too low!");

            } else {

                t2.setText("Too high!");
            }

            et.setText("");

            if (count > 5) {

                if (timer != null) {
                    timer.cancel();
                }

                score--;

                t2.setText("You lost 1 point");

                num = (int) (Math.random() * 21);

                count = 0;

                startTimer();
            }
        });
    }

    private void startTimer() {

        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(15000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(
                        "Time: " + (millisUntilFinished / 1000)
                );
            }

            @Override
            public void onFinish() {

                timerText.setText("Time: 0");

                Intent intent = new Intent(
                        MainActivity.this,
                        MainActivity2.class
                );

                intent.putExtra("score", score);
                intent.putExtra("name", name.getText().toString());

                startActivity(intent);

                finish();
            }
        };

        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (timer != null) {
            timer.cancel();
        }
    }
}