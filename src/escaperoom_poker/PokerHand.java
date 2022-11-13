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
public class PokerHand {
    
    private ArrayList<Card> hand;
    
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
        
    public String getHandType() {
        if (straight() && flush() && hand.get(4).getRank() == 14) {
            return "royal flush";
        }
        
        if (threeOfAKind() && onePair()) {
            return "full house";
        }
        
        if (straight() && flush()){
            return "straight flush";
        }
        
        if (flush()) {
            return "flush";
        }
        
        if (straight()) {
            return "straight";
        }
        
        if (fourOfAKind()){
            return "four of a kind";
        }
        
        if (twoPairs()) {
            return "two pairs";
        }
        
        if (onePair()) {
            return "one pair";
        }
        
        if (threeOfAKind()) {
            return "three of a kind";
        }

        return "high card";
    }
    
    public int getHandValue() {
        Collections.sort(hand);//cards arranged in ascending order by rank
        
        //checks for royal flush        
        if (straight() && flush() && hand.get(4).getRank() == 14) {
            return 1000;
        }
        
        //checks for straight flush
        if (straight() && flush()) {
            return 900 + hand.get(4).getRank();
        }
        
        if (fourOfAKind()) {
            return 800 + hand.get(2).getRank();
        }
        
        //checks for full house
        if (threeOfAKind() && onePair()) {
            return 700 + hand.get(3).getRank(); //not sure if this is correct
        }
        
        if (flush()) {
            return 600 + hand.get(4).getRank();
        }
        
        if (straight()) {
            return 500 + hand.get(4).getRank();
        }
        
        if (threeOfAKind()) {
            return 400 + hand.get(2).getRank();
        }
        
        if (twoPairs()) {
            return 300 + hand.get(2).getRank(); //need to fix
        }
        
        if (onePair()) {
            return 200 + hand.get(2).getRank(); //need to fix
        }
        
        return 100 + hand.get(4).getRank();
    }
}
