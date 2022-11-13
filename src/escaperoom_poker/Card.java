/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escaperoom_poker;

/**
 *
 * @author kkauf
 */
public class Card implements Comparable<Card> {
    private int rank;
    private int suit;
    
    public Card(){
        this(2,1); //default
    }
    
    public Card(int initRank, int initSuit){
        rank = initRank;
        suit = initSuit;
    }
    
    public Card(String code){
        String ranks = "??23456789TJQKA"; //2 through 9, 10, Jack Queeen, King, Ace
        String suits = "?CDHS"; //Clubs, Hearts, Spades
        
        if(code.length() == 2){
            rank = ranks.indexOf(code.substring(0,1));
            suit = suits.indexOf(code.substring(1,2));
        }
    }
    
    public int getRank() {
        return rank;
    }
    
    public int getSuit() {
        return suit;
    }
    public void setRank(int rankInit) {
        if (rank >= 2 && rank <= 14) {
            rank = rankInit;
        }
    }
    public void setSuit(int suitInit){
        if (suit >= 1 && suit <= 4){
            suit = suitInit;
        }
    }
    /**
     *
     * @param initRank
     * @param initSuit
     */
    public void setCard(int initRank, int initSuit){
        setRank(initRank);
        setSuit(initSuit);
    }  
    
    public String showCard(){
        return rankToString() + " of " + suitToString();        
    }
            
        
    public String rankToString(){
        switch(rank){
          case 1: case 14:
            return "Ace";
          case 13: 
            return "King";
          case 12:
            return "Queen";
          case 11:
            return "Jack";
          default: // 2-10
            return Integer.toString(rank);
        }
    }
    public String suitToString(){
      switch(suit){
        case 1:
          return "Clubs";
        case 2:
          return "Diamonds";
        case 3:
          return "Hearts";
        default: // 4
          return "Spades";
        }
    }
    
    @Override
    public int compareTo(Card card2){
        if(this.rank == card2.rank){
            return 0;
        }    
        else if(this.rank > card2.rank){
            return 1;
        }
        else {
            return -1;
        }
            
    }
    @Override
    public String toString(){
        return showCard();
    }
    
}
