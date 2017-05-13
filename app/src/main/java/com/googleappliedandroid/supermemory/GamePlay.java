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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class GamePlay extends AppCompatActivity {

    private int cardNumber;
    private int time;
    private List<Integer> l;
    private HashMap<Card, Integer> cardMap;
    private ImageView card;
    private Button endGame;
    private Button stopMem;
    private Game game;
    private int tries = 3;
    private Deque<Card> answer;
    private Stack<Card> previous;
    private Button left;
    private Button right;
    CardButtons button1;
    CardButtons button2;
    CardButtons button3;
    CardButtons button4;
    Intent gameOverIntent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        gameOverIntent = new Intent(GamePlay.this, GameOver.class);
        final Intent intent = getIntent();
        previous = new Stack<Card>();
        card = (ImageView) findViewById(R.id.card);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
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
                startActivity(gameOverIntent);
            }
        });

//        card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!answer.isEmpty()){
//                    card.setBackgroundResource(cardMap.get(answer.poll()));
//                }
//            }
//        });
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if (!previous.empty()) {
                    answer.addFirst(previous.pop());
                    //prev = previous.pop();
                    //Card prev = answer.removeLast();
                    card.setBackgroundResource(cardMap.get(answer.peek()));
                    //answer.addFirst(prev);
                    //Log.d("string",prev.toString());
                    //  previous.push(prev);

                }
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                if(!answer.isEmpty()) {
                    Card current = previous.push(answer.poll());
                    if(answer.isEmpty()){
                        answer.addFirst(previous.pop());
                        card.setBackgroundResource(cardMap.get(answer.peek()));
                    }
                    else{
                        card.setBackgroundResource(cardMap.get(answer.peek()));
                    }



                    //  answer.addLast(current);
                    //previous.push(current);

                }
            }
        });

        Card current = answer.peek();
        card.setBackgroundResource(cardMap.get(current));
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
                        startActivity(gameOverIntent);
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
        TextView textNum = (TextView)findViewById(R.id .number_text);
        textNum.setText(Integer.toString(cardNumber-game.getSequence().size()+1));
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
        final TextView triesText = (TextView)findViewById(R.id.tries);
        triesText.setText(Integer.toString(tries));
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
                    triesText.setText(Integer.toString(--tries));
                    if(tries == 0){
                        endGame();
                    }
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
                    triesText.setText(Integer.toString(--tries));
                    if(tries == 0){
                        endGame();
                    }
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
                    triesText.setText(Integer.toString(--tries));
                    if(tries == 0){
                        endGame();
                    }
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
                    triesText.setText(Integer.toString(--tries));
                    if(tries == 0){
                        endGame();
                    }
                }
            }
        });
    }
    public void endGame(){
            //Toast.makeText(getApplicationContext(),"Do better next time", Toast.LENGTH_LONG);

            startActivity(gameOverIntent);
    }

}
