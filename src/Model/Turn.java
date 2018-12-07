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
    private int id;
    private Player player;
    private Card card;
    private int score;
    private int lifes;
    private boolean started;
    
    public Turn(Player player) {
        this.player = player;
        this.card = null;
        this.score = Preferences.TURN_STARTING_POINTS;
        this.lifes = Preferences.DEFAULT_LIFE_QTY;
        this.started = false;
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

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public boolean isStarted() {
        return started;
    }  

}
