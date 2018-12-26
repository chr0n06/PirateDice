/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Settings.Preferences;

/**
 *
 * @author Maxime
 */
public class Turn {
    private Player player;
    private Card card;
    private int score;
    private int lifes;
    
    public Turn() {
        this.player = null;
        this.card = null;
        this.score = 0;
        this.lifes = Preferences.DEFAULT_LIFE_QTY;
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

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }
 
}
