/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escaperoom_poker;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Random;


/**
 *
 * @author kkauf
 */
public class CardDeck {
    private ArrayList<String> games;
    
    public CardDeck() {
        games = new ArrayList<String>();
        readFile();
    }
    //reads random lines from text file to set uo poker games
    public void readFile(){

        BufferedReader reader;
        List<String> listOfPokerGames = new ArrayList<>();
        try {
           //fileReader = new FileReader(pokerFile);
            reader = new BufferedReader(new FileReader("p054_poker.txt"));
            String pokerLine = reader.readLine();
            while (pokerLine != null) {
               listOfPokerGames.add(pokerLine);
               pokerLine = reader.readLine();
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("Read Error");
        }

        String[] pokerArray = listOfPokerGames.toArray(new String[0]);
        Random random = new Random();
        int lineIndex = random.nextInt(listOfPokerGames.size() -1);
        games.add(pokerArray[lineIndex]);
    }
    
    public ArrayList<Card> dealGame() {
        ArrayList<Card> cards = new ArrayList<>();
        if(!games.isEmpty()) {
            String codes = games.remove(0);
            String [] cardCodes = codes.split(" ");
            for(String code : cardCodes){
                cards.add(new Card(code));
            }
        }
        return cards;
    }

}
