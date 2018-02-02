package com.example.android.quiz;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

// Correct answer is rb2.

public class Question8Activity extends AppCompatActivity {
    private int attempt;
    private boolean isrb1Red;
    private boolean isrb3Red;
    private boolean isrb4Red;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button answerButton;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.question8);

        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        answerButton = (Button) findViewById(R.id.answer_button);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("attempt", attempt);
        outState.putBoolean("isrb1Red",isrb1Red);
        outState.putBoolean("isrb3Red",isrb3Red);
        outState.putBoolean("isrb4Red",isrb4Red);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        attempt = savedInstanceState.getInt("attempt");
        isrb1Red = savedInstanceState.getBoolean("isrb1Red");
        isrb3Red = savedInstanceState.getBoolean("isrb3Red");
        isrb4Red = savedInstanceState.getBoolean("isrb4Red");
        if(attempt == 1){
            rb2.setBackgroundColor(Color.GREEN);
            if(isrb1Red){
                rb1.setBackgroundColor(Color.RED);
            }else if(isrb3Red){
                rb3.setBackgroundColor(Color.RED);
            }else if(isrb4Red){
                rb4.setBackgroundColor(Color.RED);
            }
            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            answerButton.setText(R.string.finish_quiz);
        }else if(attempt ==2){
            setContentView(R.layout.last_layout);
            TextView textView = (TextView) findViewById(R.id.textPlayer);
            textView.setText(MainActivity.getPlayerName()+"!");
            TextView scoreView = (TextView) findViewById(R.id.score_text);
            scoreView.setText(getString(R.string.total_score) + " " + MainActivity.getScore()+ "/" + getString(R.string.max_score));
        }
    }

    @Override
    public void onBackPressed() { }

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
                Toast.makeText(this, R.string.question8_warning, Toast.LENGTH_SHORT).show();
            } else{
                if (rb2.isChecked()) {
                    MainActivity.increaseScore();
                    rb2.setBackgroundColor(Color.GREEN);
                    Toast.makeText(this, getString(R.string.correct) + " " + MainActivity.getScore(), Toast.LENGTH_SHORT).show();
                } else {
                    rb2.setBackgroundColor(Color.GREEN);
                    if(rb1.isChecked()) {
                        rb1.setBackgroundColor(Color.RED);
                        isrb1Red = true;
                    }else if(rb3.isChecked()) {
                        rb3.setBackgroundColor(Color.RED);
                        isrb3Red = true;
                    }else if(rb4.isChecked()){
                        rb4.setBackgroundColor(Color.RED);
                        isrb4Red = true;
                    }
                    Toast.makeText(this, getString(R.string.incorrect) + " " + MainActivity.getScore(), Toast.LENGTH_SHORT).show();
                }
                rb1.setClickable(false);
                rb2.setClickable(false);
                rb3.setClickable(false);
                rb4.setClickable(false);
                answerButton.setText(R.string.finish_quiz);
                attempt = 1;
            }
        }else{
            mp = MediaPlayer.create(getApplicationContext(), R.raw.applause);
            mp.start();
            setContentView(R.layout.last_layout);
            TextView textView = (TextView) findViewById(R.id.textPlayer);
            textView.setText(MainActivity.getPlayerName()+"!");
            TextView scoreView = (TextView) findViewById(R.id.score_text);
            scoreView.setText(getString(R.string.total_score) + " " + MainActivity.getScore()+ "/" + getString(R.string.max_score));
            attempt = 2;
        }
    }
}
