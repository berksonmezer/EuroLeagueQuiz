package com.example.android.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

// Correct answer is rb1.

public class Question7Activity extends AppCompatActivity {
    private int attempt;
    private boolean isrb4Red;
    private boolean isrb2Red;
    private boolean isrb3Red;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button answerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.question7);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        answerButton = (Button) findViewById(R.id.answer_button);
    }

    @Override
    public void onBackPressed() { }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("attempt", attempt);
        outState.putBoolean("isrb4Red",isrb4Red);
        outState.putBoolean("isrb2Red",isrb2Red);
        outState.putBoolean("isrb3Red",isrb3Red);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        attempt = savedInstanceState.getInt("attempt");
        isrb4Red = savedInstanceState.getBoolean("isrb4Red");
        isrb2Red = savedInstanceState.getBoolean("isrb2Red");
        isrb3Red = savedInstanceState.getBoolean("isrb3Red");
        if(attempt == 1){
            rb1.setBackgroundColor(Color.GREEN);
            if(isrb4Red){
                rb4.setBackgroundColor(Color.RED);
            }else if(isrb2Red){
                rb2.setBackgroundColor(Color.RED);
            }else if(isrb3Red){
                rb3.setBackgroundColor(Color.RED);
            }
            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            answerButton.setText(R.string.next_question);
        }
    }

    public void radioButton1Listener(View v) {
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
    }

    public void radioButton2Listener(View v) {
        rb1.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
    }

    public void radioButton3Listener(View v) {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb4.setChecked(false);
    }

    public void radioButton4Listener(View v) {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
    }

    public void answerListener(View v){
        if(attempt == 0) {
            if (!rb1.isChecked() && !rb2.isChecked() && !rb3.isChecked() && !rb4.isChecked()) {
                Toast.makeText(this, R.string.question7_warning, Toast.LENGTH_SHORT).show();
            } else{
                rb1.setBackgroundColor(Color.GREEN);
                if (rb1.isChecked()) {
                    MainActivity.increaseScore();
                    Toast.makeText(this, getString(R.string.correct) + " " + MainActivity.getScore(), Toast.LENGTH_SHORT).show();
                } else {
                    if(rb4.isChecked()) {
                        rb4.setBackgroundColor(Color.RED);
                        isrb4Red = true;
                    }else if(rb2.isChecked()) {
                        rb2.setBackgroundColor(Color.RED);
                        isrb2Red = true;
                    }else if(rb3.isChecked()){
                        rb3.setBackgroundColor(Color.RED);
                        isrb3Red = true;
                    }
                    Toast.makeText(this, getString(R.string.incorrect) + " " + MainActivity.getScore(), Toast.LENGTH_SHORT).show();
                }
                rb1.setClickable(false);
                rb2.setClickable(false);
                rb3.setClickable(false);
                rb4.setClickable(false);
                answerButton.setText(R.string.next_question);
                attempt = 1;
            }
        }else{
            Intent question8Intent = new Intent(this, Question8Activity.class);
            startActivity(question8Intent);
        }
    }
}