package com.googleappliedandroid.supermemory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText cardNumberText;
    private EditText timeText;
    private Button startButton;
    private int cardNumber;
    private int time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardNumberText = (EditText)findViewById(R.id.card_number);
        timeText = (EditText)findViewById(R.id.time_second);
        Log.d("Card1", Integer.toString(R.drawable.c1));
        Log.d("Card1", Integer.toString(R.drawable.c2));
        Log.d("Card1", Integer.toString(R.drawable.c3));
        Log.d("Card1", Integer.toString(R.drawable.c4));
        Log.d("Card1", Integer.toString(R.drawable.c5));
        Log.d("Card1", Integer.toString(R.drawable.c6));
        Log.d("Card1", Integer.toString(R.drawable.c7));
        Log.d("Card1", Integer.toString(R.drawable.c8));
        Log.d("Card1", Integer.toString(R.drawable.c9));
        Log.d("Card1", Integer.toString(R.drawable.ca));
        Log.d("Card1", Integer.toString(R.drawable.cb));
        Log.d("Card1", Integer.toString(R.drawable.cc));
        Log.d("Card1", Integer.toString(R.drawable.cd));
        Log.d("Card1", Integer.toString(R.drawable.d1));
        Log.d("Card1", Integer.toString(R.drawable.d2));
        Log.d("Card1", Integer.toString(R.drawable.d3));
        Log.d("Card1", Integer.toString(R.drawable.d4));
        Log.d("Card1", Integer.toString(R.drawable.d5));
        Log.d("Card1", Integer.toString(R.drawable.d6));
        Log.d("Card1", Integer.toString(R.drawable.d7));
        Log.d("Card1", Integer.toString(R.drawable.d8));
        Log.d("Card1", Integer.toString(R.drawable.d9));
        Log.d("Card1", Integer.toString(R.drawable.da));
        Log.d("Card1", Integer.toString(R.drawable.db));
        Log.d("Card1", Integer.toString(R.drawable.dc));
        Log.d("Card1", Integer.toString(R.drawable.dd));
        Log.d("Card1", Integer.toString(R.drawable.h1));
        Log.d("Card1", Integer.toString(R.drawable.h2));
        Log.d("Card1", Integer.toString(R.drawable.h3));
        Log.d("Card1", Integer.toString(R.drawable.h4));
        Log.d("Card1", Integer.toString(R.drawable.h5));
        Log.d("Card1", Integer.toString(R.drawable.h6));
        Log.d("Card1", Integer.toString(R.drawable.h7));
        Log.d("Card1", Integer.toString(R.drawable.h8));
        Log.d("Card1", Integer.toString(R.drawable.h9));
        Log.d("Card1", Integer.toString(R.drawable.ha));
        Log.d("Card1", Integer.toString(R.drawable.hb));
        Log.d("Card1", Integer.toString(R.drawable.hc));
        Log.d("Card1", Integer.toString(R.drawable.hd));
        Log.d("Card1", Integer.toString(R.drawable.s1));
        Log.d("Card1", Integer.toString(R.drawable.s2));
        Log.d("Card1", Integer.toString(R.drawable.s3));
        Log.d("Card1", Integer.toString(R.drawable.s4));
        Log.d("Card1", Integer.toString(R.drawable.s5));
        Log.d("Card1", Integer.toString(R.drawable.s6));
        Log.d("Card1", Integer.toString(R.drawable.s7));
        Log.d("Card1", Integer.toString(R.drawable.s8));
        Log.d("Card1", Integer.toString(R.drawable.s9));
        Log.d("Card1", Integer.toString(R.drawable.sa));
        Log.d("Card1", Integer.toString(R.drawable.sb));
        Log.d("Card1", Integer.toString(R.drawable.sc));
        Log.d("Card11", Integer.toString(R.drawable.sd));

        cardNumberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNumberText.setText("");
            }
        });
        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeText.setText("");
            }
        });
        startButton = (Button)findViewById(R.id.start);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNumber = Integer.parseInt(cardNumberText.getText().toString());
                time = Integer.parseInt(timeText.getText().toString());
                if(cardNumber>5 && time < 120){
                    Intent intent = new Intent(MainActivity.this, GamePlay.class);
                    intent.putExtra("CARD_NUMBER", cardNumber);
                    intent.putExtra("TIME", time);
                    startActivity(intent);
                }
            }
        });
    }
}
