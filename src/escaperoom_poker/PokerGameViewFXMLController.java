/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package escaperoom_poker;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


/**
 * FXML Controller class
 *
 * @author kkauf
 */

public class PokerGameViewFXMLController implements Initializable {
    
    
    @FXML
    private GridPane pokerBoard;
    @FXML
    private ChoiceBox playerBet;
    @FXML 
    private TextField field;
    @FXML
    private Label player1Card1;
    @FXML
    private Label player1Card2;
    @FXML
    private Label player1Card3;
    @FXML
    private Label player1Card4;
    @FXML
    private Label player1Card5;
    @FXML
    private Label player2Card1;
    @FXML
    private Label player2Card2;
    @FXML
    private Label player2Card3;
    @FXML
    private Label player2Card4;
    @FXML
    private Label player2Card5;
    @FXML
    private Label player1;   
    @FXML
    private Label betLabel;
    @FXML
    private Label messageBet;
    @FXML
    private Label winLoseMessage;
    @FXML
    private Button nextRound;
    @FXML    
    private ImageView imgControl;
       
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pokerBoard.setStyle("-fx-background-color: #008000;");
        CardDeck pokerGame = new CardDeck();
        ArrayList<Card> allCards = pokerGame.dealGame();
        List<Card> hand1 = allCards.subList(0, 5);
        List<Card> hand2 = allCards.subList(5, 10);
        ArrayList<Card> arrayHand1 = new ArrayList<>();
        arrayHand1.addAll(hand1);
        ArrayList<Card> arrayHand2 = new ArrayList<>();
        arrayHand2.addAll(hand2);
        player1Card1.setText(arrayHand1.get(0).showCard());
        player1Card2.setText(arrayHand1.get(1).showCard());
        player1Card3.setText(arrayHand1.get(2).showCard());
        player1Card4.setText(arrayHand1.get(3).showCard());
        player1Card5.setText(arrayHand1.get(4).showCard());
        player2Card1.setText("?");
        player2Card2.setText("?");
        player2Card3.setText("?");
        player2Card4.setText("?");
        player2Card5.setText("?"); 
        player1.setText("Player 1 Cards");

        PokerHand player1 = new PokerHand(arrayHand1);
        PokerHand player2 = new PokerHand(arrayHand2);
        player1.checkHand();
        player2.checkHand();
        player1.checkHandType();
        player2.checkHandType();
        
        betLabel.setText("Place a bet");
        int player1WinningsTotal = 0;
        playerBet.setItems(FXCollections.observableArrayList("Fold","Bet $10")); 
        String roundMessage[];
        roundMessage = new String[]{ "player 1 folds", "player 1 bets $10"};
                
        playerBet.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue ) -> {
                    
                    messageBet.setText(roundMessage[newValue.intValue()]);
                    player2Card1.setText(arrayHand2.get(0).showCard());
                    player2Card2.setText(arrayHand2.get(1).showCard());
                    player2Card3.setText(arrayHand2.get(2).showCard());
                    player2Card4.setText(arrayHand2.get(3).showCard());
                    player2Card5.setText(arrayHand2.get(4).showCard());
                    playerBet.hide();                    
                    if(player1.compareTo(player2) > 0){
                        winLoseMessage.setText("Player wins with " + player1.getHandType());

                    }
                    else if (player1.compareTo(player2) < 0){
                        winLoseMessage.setText("player loses with " + player1.getHandType());
                    }
                    else {
                        winLoseMessage.setText("Players Tied");
                    }
                    
        
     
        });
        
        
        nextRound.setText("Next Round");
//        nextRound.setOnAction(new EventHandler<ActionEvent>() {
//            @Override public void handle(ActionEvent e) {
//               restart(initialize(URL url, ResourceBundle rb));
//            }
//        });
        

    
        


    }
//    @Override
//    public void handle(ActionEvent event) {
//        Button clicked = (Button)event.getSource();
////        System.out.println(clicked.idProperty().getValue());
//        if (clicked.idProperty().getValue().equals("nextRound")) {
//            nextRound.setText("");
//        } 
//
//    }
    
}
