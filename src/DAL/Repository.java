package DAL;

import Model.Card;
import Model.Dice;
import Model.Player;
import Settings.Preferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chr0n06
 */
public class Repository {

    private static Repository repository = null;

    private List<Dice> dices;
    private List<Player> players;
    private List<Card> cards;

    // private constructor restricted to this class itself 
    private Repository() {
        this.dices = new ArrayList<>();
        this.players = new ArrayList<>();
        this.cards = new ArrayList<>();
        initializeBoard();
    }

    public static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    private void initializeBoard() {
        generateDices();
        generatePlayers();
        generateCards();
    }

    private void generateDices() {
        for (int i = 0; i < 8; i++) {
            dices.add(new Dice());
        }
    }

    private void generatePlayers() { 
        for(int i=0;i<Preferences.PLAYER_NAMES.length;i++){
            players.add(new Player(Preferences.PLAYER_NAMES[i]));
        }
        Collections.shuffle(players);
    }

    private void generateCards() {
        
        
        /*Older verison of the card genarator*/
       /* for (int i = 0; i < 35; i++) {
            if (i <= 4) {
                cards.add(new Card("Piece of Gold"));
            } else if (i <= 9) {
                cards.add(new Card("Brilliant Diamond"));
            } else if (i <= 12) {
                cards.add(new Card("Treasure"));
            } else if (i <= 14) {
                cards.add(new Card("Witch"));
            } else if (i <= 17) {
                cards.add(new Card("Pirate"));
            } else if (i <= 23) {
                cards.add(new Card("Monkey & Parrot"));
            } else if (i <= 26) {
                cards.add(new Card("Simple Skull"));
            } else if (i <= 28) {
                cards.add(new Card("Double Skulls"));
            } else if (i <= 31) {
                cards.add(new Card("Pirate Ship easy"));
            } else if (i <= 33) {
                cards.add(new Card("Pirate Ship 2"));
            } else if (i == 34) {
                cards.add(new Card("Pirate Ship 3"));
            }
        }*/
        Collections.shuffle(cards);
    }

    public List<Dice> getDices() {
        return dices;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getCards() {
        return cards;
    }    
    
}
