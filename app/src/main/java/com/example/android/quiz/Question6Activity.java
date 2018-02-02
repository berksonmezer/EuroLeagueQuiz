package com.example.android.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

// Correct answer is cb3, cb6 and cb8.

public class Question6Activity extends AppCompatActivity {
    private int attempt = 0;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private CheckBox cb6;
    private CheckBox cb7;
    private CheckBox cb8;
    private Button answerButton;
    private boolean iscb1Red;
    private boolean iscb2Red;
    private boolean iscb4Red;
    private boolean iscb5Red;
    private boolean iscb7Red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.question6);
        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);
        cb3 = (CheckBox) findViewById(R.id.cb3);
        cb4 = (CheckBox) findViewById(R.id.cb4);
        cb5 = (CheckBox) findViewById(R.id.cb5);
        cb6 = (CheckBox) findViewById(R.id.cb6);
        cb7 = (CheckBox) findViewById(R.id.cb7);
        cb8 = (CheckBox) findViewById(R.id.cb8);
        answerButton = (Button) findViewById(R.id.answer_button);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("attempt", attempt);
        outState.putBoolean("iscb1Red",iscb1Red);
        outState.putBoolean("iscb2Red",iscb2Red);
        outState.putBoolean("iscb4Red",iscb4Red);
        outState.putBoolean("iscb5Red",iscb5Red);
        outState.putBoolean("iscb7Red",iscb7Red);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        attempt = savedInstanceState.getInt("attempt");
        iscb1Red = savedInstanceState.getBoolean("iscb1Red");
        iscb2Red = savedInstanceState.getBoolean("iscb2Red");
        iscb4Red = savedInstanceState.getBoolean("iscb4Red");
        iscb5Red = savedInstanceState.getBoolean("iscb5Red");
        iscb7Red = savedInstanceState.getBoolean("iscb7Red");
        if(attempt == 1){
            cb3.setBackgroundColor(Color.GREEN);
            cb6.setBackgroundColor(Color.GREEN);
            cb8.setBackgroundColor(Color.GREEN);
            if(iscb1Red)
                cb1.setBackgroundColor(Color.RED);
            if(iscb4Red)
                cb4.setBackgroundColor(Color.RED);
            if(iscb5Red)
                cb5.setBackgroundColor(Color.RED);
            if(iscb7Red)
                cb7.setBackgroundColor(Color.RED);
            cb1.setClickable(false);
            cb2.setClickable(false);
            cb3.setClickable(false);
            cb4.setClickable(false);
            cb5.setClickable(false);
            cb6.setClickable(false);
            cb7.setClickable(false);
            cb8.setClickable(false);
            answerButton.setText(R.string.next_question);
        }
    }

    @Override
    public void onBackPressed() { }

    public void answerListener(View v){
        int numberOfCheckedBoxes = 0;
        if(attempt == 0){
            if(cb1.isChecked())
                numberOfCheckedBoxes++;
            if(cb2.isChecked())
                numberOfCheckedBoxes++;
            if(cb3.isChecked())
                numberOfCheckedBoxes++;
            if(cb4.isChecked())
                numberOfCheckedBoxes++;
            if(cb5.isChecked())
                numberOfCheckedBoxes++;
            if(cb6.isChecked())
                numberOfCheckedBoxes++;
            if(cb7.isChecked())
                numberOfCheckedBoxes++;
            if(cb8.isChecked())
                numberOfCheckedBoxes++;
            if(numberOfCheckedBoxes != 3){
                Toast.makeText(this, R.string.question6_warning, Toast.LENGTH_SHORT).show();
            }else if(numberOfCheckedBoxes == 3){
                cb3.setBackgroundColor(Color.GREEN);
                cb6.setBackgroundColor(Color.GREEN);
                cb8.setBackgroundColor(Color.GREEN);
                if(cb3.isChecked() && cb6.isChecked() && cb8.isChecked()){
                    MainActivity.increaseScore();
                    Toast.makeText(this, getString(R.string.correct) + " " + MainActivity.getScore(), Toast.LENGTH_SHORT).show();
                }else{
                    if(cb1.isChecked()) {
                        cb1.setBackgroundColor(Color.RED);
                        iscb1Red = true;
                    }
                    if(cb2.isChecked()) {
                        cb2.setBackgroundColor(Color.RED);
                        iscb2Red = true;
                    }
                    if(cb4.isChecked()) {
                        cb4.setBackgroundColor(Color.RED);
                        iscb4Red = true;
                    }
                    if(cb5.isChecked()) {
                        cb5.setBackgroundColor(Color.RED);
                        iscb5Red = true;
                    }
                    if(cb7.isChecked()) {
                        cb7.setBackgroundColor(Color.RED);
                        iscb7Red = true;
                    }
                    Toast.makeText(this, getString(R.string.incorrect) + + MainActivity.getScore(), Toast.LENGTH_SHORT).show();
                }
                cb1.setClickable(false);
                cb2.setClickable(false);
                cb3.setClickable(false);
                cb4.setClickable(false);
                cb5.setClickable(false);
                cb6.setClickable(false);
                cb7.setClickable(false);
                cb8.setClickable(false);
                attempt = 1;
                answerButton.setText(R.string.next_question);
            }
        }else{
            Intent question7Intent = new Intent(this, Question7Activity.class);
            startActivity(question7Intent);
        }
    }
}