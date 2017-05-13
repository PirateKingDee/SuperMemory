package com.googleappliedandroid.supermemory;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Queue;

public class GamePlay extends AppCompatActivity {

    private int cardNumber;
    private int time;
    private List<Integer> l;
    private HashMap<Card, Integer> cardMap;
    private ImageView card;
    private Button endGame;
    private Button stopMem;
    private Game game;
    private Queue<Card> answer;
    CardButtons button1;
    CardButtons button2;
    CardButtons button3;
    CardButtons button4;
    Intent mainActivityIntent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        mainActivityIntent = new Intent(GamePlay.this, MainActivity.class);
        final Intent intent = getIntent();
        card = (ImageView) findViewById(R.id.card);
        stopMem = (Button) findViewById(R.id.stop_memorize);
        cardNumber = intent.getIntExtra("CARD_NUMBER", 5);
        time = intent.getIntExtra("TIME", 60);
        cardMap = new HashMap<>();
        game = new Game();
        mapCards(game.getDeck());
        try {
            game.genSequence(cardNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        answer = new LinkedList<>(game.getSequence());
        stopMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(mainActivityIntent);
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answer.isEmpty()){
                    card.setBackgroundResource(cardMap.get(answer.poll()));
                }
            }
        });

        card.setBackgroundResource(cardMap.get(answer.poll()));
        startMemorizing(time);


    }
    public void mapCards(ArrayList<Card> allCard){
        int imageID = 2130837587;
//        int last = 2130837648;
        for(int i = 0; i< 52; i++){
           cardMap.put(allCard.get(i), imageID);
            if(imageID == 2130837625){
                imageID = 2130837636;
            }
            else{
                imageID++;
            }
        }
    }

    public void startMemorizing(int sec){

        int timer = sec*1000;
        Handler timerHandle = new Handler();
        Runnable timerRunnable = new Runnable() {

            @Override
            public void run() {
                card.setEnabled(false);
                card.setVisibility(View.INVISIBLE);
                setContentView(R.layout.activity_restore_order);
                setupChoiceButtons();
                endGame = (Button)findViewById(R.id.end_game);
                endGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getApplicationContext(),"Do better next time", Toast.LENGTH_LONG);
                        startActivity(mainActivityIntent);
                    }
                });
                //updateChoices();
            }
        };
        timerHandle.postDelayed(timerRunnable, timer);
    }

//    public void getAnswer(){
////        CardButtons[] buttons = {(CardButtons) findViewById(R.id.a),(CardButtons)findViewById(R.id.b),(CardButtons)findViewById(R.id.c),(CardButtons)findViewById(R.id.d)};
////        for(int i = 0; i < buttons.length; i++){
////            buttons[i].setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    if(v.==game.getSequence().poll())
////                }
////            });
////        }
//    }

    public void updateChoices(CardButtons[] buttons){
        Card[] choices = game.guesses();
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setBackgroundResource(cardMap.get(choices[i]));
            buttons[i].setCard(choices[i]);
            //buttons[i].getBackground().equals(buttons[i+1].getBackground());
        }
    }

    public void setupChoiceButtons(){
        button1 = (CardButtons) findViewById(R.id.a);
        button2 = (CardButtons) findViewById(R.id.b);
        button3 = (CardButtons) findViewById(R.id.c);
        button4 = (CardButtons) findViewById(R.id.d);
        final CardButtons[] buttons = {button1, button2, button3, button4};
        updateChoices(buttons);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getSequence().isEmpty() && button1.getCard().equals( game.getSequence().peek())){
                    game.getSequence().poll();
                    if(!game.getSequence().isEmpty()){
                        updateChoices(buttons);
                    }
                    else if(game.getSequence().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Good Job", Toast.LENGTH_SHORT);
                        endGame();
                    }
                }

                else{
                    Toast.makeText(getApplicationContext(), "Wrong, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getSequence().isEmpty() && button2.getCard().equals( game.getSequence().peek())){
                    game.getSequence().poll();
                    if(!game.getSequence().isEmpty()){
                        updateChoices(buttons);
                    }
                    else if(game.getSequence().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Good Job", Toast.LENGTH_SHORT);
                        endGame();
                    }
                }

                else{
                    Toast.makeText(getApplicationContext(), "Wrong, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getSequence().isEmpty() && button3.getCard().equals( game.getSequence().peek())){
                    game.getSequence().poll();
                    if(!game.getSequence().isEmpty()){
                        updateChoices(buttons);
                    }
                    else if(game.getSequence().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Good Job", Toast.LENGTH_SHORT);
                        endGame();
                    }

                }

                else{
                    Toast.makeText(getApplicationContext(), "Wrong, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!game.getSequence().isEmpty() && button4.getCard().equals( game.getSequence().peek())){
                    game.getSequence().poll();
                    if(!game.getSequence().isEmpty()){
                        updateChoices(buttons);
                    }
                    else if(game.getSequence().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Good Job", Toast.LENGTH_SHORT);
                        endGame();
                    }
                }

                else{
                    Toast.makeText(getApplicationContext(), "Wrong, try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void endGame(){
            //Toast.makeText(getApplicationContext(),"Do better next time", Toast.LENGTH_LONG);

            startActivity(mainActivityIntent);
    }

}
