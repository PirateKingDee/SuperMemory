package com.googleappliedandroid.supermemory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.googleappliedandroid.supermemory.R.id.card;
import static com.googleappliedandroid.supermemory.R.id.start;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Intent intent = getIntent();
        boolean win = intent.getBooleanExtra("WinStatus", false);
        TextView header = (TextView)findViewById(R.id.title);
        if(win){
            header.setText("Congratulations!!!");
        }

        Handler timerHandle = new Handler();
        Runnable timerRunnable = new Runnable() {

            @Override
            public void run() {
                Intent mainActivityIntent = new Intent(GameOver.this, MainActivity.class);
                startActivity(mainActivityIntent);
                //updateChoices();
            }
        };
        timerHandle.postDelayed(timerRunnable, 3000);
    }
}
