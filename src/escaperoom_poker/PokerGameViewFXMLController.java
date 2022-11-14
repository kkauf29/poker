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
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
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
    private Label player2;
    @FXML
    private Label betLabel;
    @FXML
    private Label winLoseMessage;
    @FXML
    private Label messageBet;
    @FXML
    private Button nextRound;
    @FXML    
    private ImageView imgControl;
    @FXML
    private Label gameTotalLabel;
    
    private PokerGame currentGame;
    
    private int winTotal;
    private ArrayList<Card> player1Cards;
    private ArrayList<Card> player2Cards;
    
    private void startNewGame() {
        currentGame = new PokerGame();
        
        player1Cards = currentGame.playerCards(1);
        player2Cards = currentGame.playerCards(2);
        
        player1Card1.setText(player1Cards.get(0).showCard());
        player1Card2.setText(player1Cards.get(1).showCard());
        player1Card3.setText(player1Cards.get(2).showCard());
        player1Card4.setText(player1Cards.get(3).showCard());
        player1Card5.setText(player1Cards.get(4).showCard());
        player2Card1.setText("?");
        player2Card2.setText("?");
        player2Card3.setText("?");
        player2Card4.setText("?");
        player2Card5.setText("?"); 
        player1.setText("Player 1 Cards");

        player2.setText("Player 2 Cards");
        messageBet.setText(" ");
        winLoseMessage.setText(" ");
        gameTotalLabel.setText("player total: " + winTotal);
        if (winTotal >= 50){
            winTotal = 0;
            nextRound.setText("New Game");
            
        }
         
    }
    
    private void setUpBetBox(){
        betLabel.setText("Place a bet");
        
        playerBet.setItems(FXCollections.observableArrayList("Fold","Bet $10")); 
        String roundMessage[];
        roundMessage = new String[]{"player 1 folds", "player 1 bets $10"};
                
        playerBet.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
                    
                    player2Card1.setText(player2Cards.get(0).showCard());
                    player2Card2.setText(player2Cards.get(1).showCard());
                    player2Card3.setText(player2Cards.get(2).showCard());
                    player2Card4.setText(player2Cards.get(3).showCard());
                    player2Card5.setText(player2Cards.get(4).showCard());
                    messageBet.setText("Player 2: " + currentGame.playerHandDescription(2));
                    

                    if (newValue.equals(1)) {
                        int winner = currentGame.getWinner();
                        if (winner == 1) {
                            winLoseMessage.setText("Player 1 wins with " + currentGame.playerHandDescription(1));
                            winTotal += 10;
                        } else if (winner == 2) {
                            winLoseMessage.setText("Player 1 loses with " + currentGame.playerHandDescription(1));
                            winTotal -= 10;
                        } else {
                            winLoseMessage.setText("Players tied");
                        }
                        gameTotalLabel.setText("player total: " + winTotal);    

                        playerBet.setDisable(true);
                    }
        });

    }
    
    public void resetPlayerBet() {
        playerBet.getSelectionModel().clearSelection();
        playerBet.setDisable(false);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pokerBoard.setStyle("-fx-background-color: #00A000;");
        
        winTotal = 0;
        startNewGame();
        setUpBetBox();
        
        nextRound.setText("Next Round");
        nextRound.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                startNewGame();
                resetPlayerBet();
                player2Card1.setText("?");
                player2Card2.setText("?");
                player2Card3.setText("?");
                player2Card4.setText("?");
                player2Card5.setText("?");
                messageBet.setText("");
                nextRound.setText("Next Round");
            }
        });
    }
}
