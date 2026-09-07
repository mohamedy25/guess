package com.example.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView t1;
    private TextView t2;
    private Button bt;
    private EditText et;

    private int num;
    private int score = 0;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.textView);
        t2 = findViewById(R.id.textView2);
        bt = findViewById(R.id.button);
        et = findViewById(R.id.editTextText);

        num = (int) (Math.random() * 21);

        bt.setOnClickListener(v -> {

            String input = et.getText().toString();

            if (input.isEmpty()) {
                t2.setText("Enter a number!");
                return;
            }

            int guess = Integer.parseInt(input);

            if (guess < 0 || guess > 20) {
                t2.setText("Enter a number from 0 to 20!");
                et.setText("");
                return;
            }

            count++;

            if (guess == num) {

                score++;

                t2.setText("Correct!");

                num = (int) (Math.random() * 21);

                count = 0;

            } else if (guess < num) {

                t2.setText("Too low!");

            } else {

                t2.setText("Too high!");
            }

            et.setText("");

            if (count > 5) {

                Intent intent = new Intent(
                        MainActivity.this,
                        MainActivity2.class
                );

                intent.putExtra("score", score);

                startActivity(intent);

                finish();
            }
        });
    }
}