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
    private boolean endGameActivated; // At least one player has at leats 6000 pts! 
    private Player playerWhoActivatedEndGame;

    /* Used in Death Island while the player drop a Death, 
    the minusLife is activated and the player is allowed to continue.
     */
    private boolean minusLife;

    private boolean initiated;

    public Turn() {
        this.player = null;
        this.card = null;
        this.score = 0;
        this.lifes = Preferences.DEFAULT_LIFE_QTY;
        this.minusLife = true;
        this.initiated = false;
        
        this.endGameActivated = false;
        this.playerWhoActivatedEndGame = null;

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

    public boolean isInitiated() {
        return initiated;
    }

    public void setInitiated(boolean initiated) {
        this.initiated = initiated;
    }

    public boolean isMinusLife() {
        return minusLife;
    }

    public void setMinusLife(boolean minusLife) {
        this.minusLife = minusLife;
    }

    public boolean isEndGameActivated() {
        return endGameActivated;
    }

    public void setEndGameActivated(boolean endGameActivated) {
        this.endGameActivated = endGameActivated;
    }

    public Player getPlayerWhoActivatedEndGame() {
        return playerWhoActivatedEndGame;
    }

    public void setPlayerWhoActivatedEndGame(Player playerWhoActivatedEndGame) {
        this.playerWhoActivatedEndGame = playerWhoActivatedEndGame;
    }
 
}
