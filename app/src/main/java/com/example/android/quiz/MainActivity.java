package com.example.android.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.WindowManager.*;

public class MainActivity extends AppCompatActivity {
    private static String playerName;
    private static int score;
    private static MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN,
                LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.feel_devotion);
        mp.start();
    }

    public void startButtonListener(View v) {
        EditText editText = (EditText) findViewById(R.id.player_name);
        playerName = editText.getText().toString();
        if(playerName.equals("")){
            Toast.makeText(this, R.string.please_enter_name, Toast.LENGTH_SHORT).show();
        }else {
            Intent question1Intent = new Intent(this, Question1Activity.class);
            startActivity(question1Intent);
        }

    }
    public static String getPlayerName(){
        return playerName;
    }
    public static int getScore(){
        return score;
    }
    public static void increaseScore(){
        score += 10;
    }

}