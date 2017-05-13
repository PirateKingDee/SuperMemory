package com.googleappliedandroid.supermemory;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.googleappliedandroid.supermemory.R.id.card;
import static com.googleappliedandroid.supermemory.R.id.start;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

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
