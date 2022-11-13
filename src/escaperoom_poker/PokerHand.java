/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escaperoom_poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author kkauf
 */
public class PokerHand implements Comparable<PokerHand> {
    
    private ArrayList<Card> hand;
    private int handValue;
    private String handType;
    
    
    public PokerHand(ArrayList<Card> hand) {
       this.hand = hand;         
    }
    
            
    public boolean flush() {
        boolean sameSuit = true;
        int suitToCheck = hand.get(0).getSuit();
        for (int i = 1; i < hand.size(); i++){
            if(hand.get(i).getSuit() != suitToCheck){
                sameSuit = false;
                break;
            }
        }
        return sameSuit;
    }
    
    public boolean straight() {
        boolean straightHand = true;
        Collections.sort(hand); //ascending sort based upon ranks
        for(int i = 0; i <hand.size() -1; i++) {
            if(hand.get(i).getRank() +1 != hand.get(i+1).getRank()) {
                straightHand = false;
                break;
            }
        }
        return straightHand;
    }    
    
    public boolean threeOfAKind() {
        boolean threeKind = false;
        Collections.sort(hand);
        int match = -1;
        for(int i = 0; i <hand.size() -3; i++) {
            if(hand.get(i).getRank() == hand.get(i+1).getRank()) {
                match = hand.get(i).getRank();
                if (match == hand.get(i+2).getRank()){
                    threeKind = true;
                    break;
                }
                else {
                    threeKind = false;                    
                    
                }
                
            }
        }
        return threeKind;    
    }
    

    public boolean onePair() {
        boolean onlyPair = false;
        Collections.sort(hand);
        for(int i = 0; i <hand.size() -1; i++) {
            if(hand.get(i).getRank() == hand.get(i+1).getRank()) {
                onlyPair = true;
                break;
            }
        } 
        return onlyPair;    
    }
    //make sure this method won't have an index out bounds
    public boolean fourOfAKind() {
        boolean fourKind = false;
        int matchFour;
        Collections.sort(hand);
        matchFour = hand.get(0).getRank();
        for (int i = 0; i < hand.size() - 4; i++) {
            if (hand.get(i).getRank() == hand.get(i+1).getRank()){
                matchFour = hand.get(i).getRank();
                if ((matchFour == hand.get(i+3).getRank()) && (matchFour == hand.get(i+2).getRank())) {
                    fourKind = true;
                    break;
                }
            }   
        }
        return fourKind;     
    }
        
    
    public boolean twoPairs() {
        boolean pair1 = false;
        boolean pair2 = false;
        boolean both = false;
        Collections.sort(hand);        
        if((hand.get(0).getRank() == hand.get(1).getRank()) || (hand.get(1).getRank() == hand.get(3).getRank())) {
            pair1 = true;     
            if((hand.get(2).getRank() == hand.get(3).getRank()) || (hand.get(3).getRank() == hand.get(4).getRank())){
                    pair2 = true;
                    
                }
            }
            
         
        if(pair1 == true && pair2 == true){
            both = true;
        }
        return both;
    }    
        
    public void checkHandType() {
        if (straight() && flush() && hand.get(4).getRank() == 14) {
            handType = "royal flush";

        }
        else if (threeOfAKind() && onePair()) {
            handType = "full house";
        }
        
        else if (straight() && flush()){
            handType = "straight flush";
        }
        
        else if (flush()) {
            handType = "flush";
        }
        else if (straight()) {
            handType = "straight";
        }
        else if (fourOfAKind()){
            handType = "four of a kind";
        }
        else if (twoPairs()) {
            handType = "two pairs";
        }
        else if (onePair()) {
            handType = "one pair";
        }
        else if (threeOfAKind()) {
            handType = "three of a kind";
        }

        else {
            handType = "high card";
        }
        
    }
    public String getHandType(){
        return handType;
    }
        
    
    
    public void checkHand() {
        Collections.sort(hand);//cards arranged in ascending order by rank
        
        //checks for royal flush        
        if(straight() && flush() && hand.get(4).getRank() == 14) {
            handValue = 1000;
        }
        //checks for straight flush
        else if (straight() && flush()) {
            handValue = 900 + hand.get(4).getRank();
        }
        else if (fourOfAKind()) {
            handValue = 800 + hand.get(2).getRank();
        }
        //checks for full house
        else if (threeOfAKind() && onePair()) {
            handValue = 700 + hand.get(3).getRank(); //not sure if this is correct
        }
        else if (flush()) {
            handValue = 600 + hand.get(4).getRank();
        }
        else if (straight()) {
            handValue = 500 + hand.get(4).getRank();
        }
        else if (threeOfAKind()) {
            handValue = 400 + hand.get(2).getRank();
        }
        else if (twoPairs()) {
            handValue = 300 + hand.get(2).getRank(); //need to fix
        }
        else if (onePair()) {
            handValue = 200 + hand.get(2).getRank(); //need to fix
        }    
        else {
            handValue = 100 + hand.get(4).getRank();
        }
        
    }
    public int getHandValue() {
        return handValue;
    }
    
    @Override
    public int compareTo(PokerHand player2) {
        
        if(this.handValue == player2.handValue){
            return 0;
        }
        else if(this.handValue > player2.handValue){
            return 1;
        }
        return -1;
    }
    
    
    
}
