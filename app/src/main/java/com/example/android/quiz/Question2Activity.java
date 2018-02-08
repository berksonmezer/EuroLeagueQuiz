package com.example.android.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Question2Activity extends AppCompatActivity {
    private int attempt;
    private EditText editText;
    private Button answerButton;
    private boolean isEditTextGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.question2);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        editText = (EditText) findViewById(R.id.edit_text);
        answerButton = (Button) findViewById(R.id.answer_button);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("attempt", attempt);
        outState.putBoolean("isEditTextGreen",isEditTextGreen);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        attempt = savedInstanceState.getInt("attempt");
        isEditTextGreen = savedInstanceState.getBoolean("isEditTextGreen");
        if(attempt == 1){
            if(isEditTextGreen){
                editText.setBackgroundColor(Color.GREEN);
            }else{
                editText.setBackgroundColor(Color.RED);
            }
            editText.setFocusable(false);
            answerButton.setText(R.string.next_question);
        }
    }

    @Override
    public void onBackPressed() { }

    public void answerListener(View v){
        if(attempt == 0) {
            if(editText.getText().toString().equals("")){
                Toast.makeText(this, R.string.question2_warning, Toast.LENGTH_SHORT).show();
            }else{
                if(editText.getText().toString().equals(getString(R.string.question2_correct_answer_short))){
                    MainActivity.increaseScore();
                    editText.setBackgroundColor(Color.GREEN);
                    isEditTextGreen = true;
                    Toast.makeText(this, getString(R.string.correct) + " " + MainActivity.getScore(), Toast.LENGTH_SHORT).show();
                }else{
                    editText.setBackgroundColor(Color.RED);
                    editText.setText(R.string.question2_correct_answer_long);
                    Toast.makeText(this, getString(R.string.incorrect) + " " + MainActivity.getScore(), Toast.LENGTH_SHORT).show();
                }
                editText.setFocusable(false);
                answerButton.setText(R.string.next_question);
                attempt = 1;
            }
        }else{
            Intent question3Intent = new Intent(this, Question3Activity.class);
            startActivity(question3Intent);
        }
    }
}
