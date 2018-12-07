/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BLL.Services;
import Settings.Preferences;
import java.util.Random;

/**
 *
 * @author Maxime
 */
public class Turn {
    private int id;
    private Player player;
    private Card card;
    private int score;
    
    public Turn(Player player) {
        this.player = player;
        this.card = null;
        this.score = 0;
        
    }    
    
    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
    
}
