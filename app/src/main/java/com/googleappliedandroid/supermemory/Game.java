package com.googleappliedandroid.supermemory;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
	private int memorizeTime;
	private ArrayList<Card> deck;
	private Queue <Card> sequence;
	public Game () { //Constructor - this also creates a full deck of 52 cards in order
		sequence = new LinkedList<Card>();
		//same as restoreDeck?
		char [] suits = {'c', 'd', 'h', 's'};
		int cardNum = 0, suitNum = 0;
		deck = new ArrayList();
		for (int i = 0; i < 52; i++) {
			deck.add(new Card (cardNum, suits[suitNum]));
			cardNum++;
			if (cardNum == 13) {
				cardNum = 0;
				suitNum++;
			}
			
		}
	}
	public void restoreDeck () { //Restore the deck to full 52 cards in order
		char [] suits = {'d', 'c', 'h', 's'};
		int cardNum = 0, suitNum = 0;
		deck = new ArrayList();
		for (int i = 0; i < 52; i++) {
			deck.add(new Card (cardNum, suits[suitNum]));
			cardNum++;
			if (cardNum == 13) {
				cardNum = 0;
				suitNum++;
			}
			
		}
	}

	public void display() { //Display the cards in the deck... used for testing
		System.out.println(deck.size());
		for (int i = 0; i < deck.size(); i++) {
			System.out.println(deck.get(i).toString());
		}
	}
	public void genSequence(int n) throws InterruptedException { //Generate the random sequence of numbers and store in queue
		Random rand = new Random();
		for (int i = 0; i < n; i++) {
			int cardNum = rand.nextInt(deck.size()); 
			sequence.add(deck.get(cardNum)); //add card to queue
			System.out.println(deck.get(cardNum).toString()); //output it
			deck.remove(cardNum); //remove this card from the deck
			//TimeUnit.SECONDS.sleep(1); //wait until display next card
			//System.out.println("\n\n\n\n\n\n\n\n\n\n"); //"clear" output
		}
	}
	
	public Card[] guesses () { //will return the correct answer
		Card answer = sequence.peek(); //store answer by peaking 
		Card [] arr = new Card [4]; //these will be your possible answers
		Random rand = new Random();
		int correct = rand.nextInt(4); //decide where to store the answer
		arr[correct] = answer; 
		for (int i = 0; i < 4; i++) {
			if (arr[i] != answer) {
				arr[i] = deck.get(rand.nextInt(deck.size()));//put random card as an options
			}
			//System.out.println("Card " + (i + 1) + "  " + arr[i].toString());
		}
		//sequence.remove(); //move onto the next card in the sequence
		return arr;
	}
	public ArrayList<Card> getDeck(){
		return deck;
	}
	public Queue<Card> getSequence(){
		return sequence;
	}
//	public Card getRandomCard(){
//		Random rand = new Random();
//		return deck.get(rand.nextInt(52));
////	}
//	public static void main(String[] args) throws InterruptedException {
//		Game test = new Game();
//		char key = 'y';
//		while (key == 'y') {
//			Scanner in = new Scanner(System.in);
//			test.restoreDeck();
//			System.out.print("Enter the level: ");
//			int seqNum = in.nextInt();
//			System.out.println("Sequence: " );
//			test.genSequence(seqNum);
//			System.out.println("End of sequence" );
//			for (int i = 0; i < seqNum; i++) {
//				//int answer = test.guesses();
//				int guess = in.nextInt();
//				//while (guess != answer) {
//					guess = in.nextInt();
//				}
//			}
//		}
//
//	}

}
