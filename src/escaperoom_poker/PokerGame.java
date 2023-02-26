/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escaperoom_poker;

import java.util.ArrayList;

/**
 *
 * @author kkauf
 */
public class PokerGame {
    private CardDeck cardDeck;
    private ArrayList<Card> deal;
    private PokerHand player1Hand;
    private PokerHand player2Hand;
    
    public PokerGame() {
        cardDeck = new CardDeck();
        deal = cardDeck.dealGame();
        
        player1Hand = new PokerHand(this.playerCards(1));
        player2Hand = new PokerHand(this.playerCards(2));
    }
    //divides 10 Card objects into two hands
    public ArrayList<Card> playerCards(int player) {
        if (player == 1) {
            return new ArrayList<>(deal.subList(0, 5));
        } else if (player == 2) {
            return new ArrayList<>(deal.subList(5, 10));
        } else {
            throw new IllegalArgumentException("player number should be 1 or 2");
        }
    }
    
    public String playerHandDescription(int player) {
        if (player == 1) {
            return player1Hand.getHandType();
        } else if (player == 2) {
            return player2Hand.getHandType();
        } else {
            throw new IllegalArgumentException("player number should be 1 or 2");
        }
    }
    
    public int getWinner() {
        int player1Value = player1Hand.getHandValue();
        int player2Value = player2Hand.getHandValue();
        
        if (player1Value == player2Value) {
            return 0; // tie
        }
        
        if (player1Value > player2Value) {
            return 1;
        }
        
        return 2;
    }

}
