/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Repository;
import Model.Card;
import Model.Dice;
import Model.Player;
import Model.Turn;
import Settings.Preferences;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author chr0n06
 */
public class Services {

    public Repository repo;

    private static Services services = null;

    public Services() {
        repo = Repository.getInstance();
    }

    public static Services getInstance() {
        if (services == null) {
            services = new Services();
        }
        return services;
    }

    public List<Dice> startGame() {
        return repo.getDices();
    }

    public void rollDices() {
        for (Dice dice : repo.getDices()) {
            if (!dice.getIsDeath()) { //Conditional will be implemented in Turn Class in the future!
                dice.rollDice();
            }
        }
    }

    public void pickACard(Turn turn) {
        if (Preferences.CARD_PACK_INDEX <= Preferences.CARD_PACK_QTY) {
            turn.setCard(getAllCards().get(Preferences.CARD_PACK_INDEX));
            Preferences.CARD_PACK_INDEX++;
        }else {
            resetCardPackIndex();
        }
    }
    
    private void resetCardPackIndex() {
        Preferences.CARD_PACK_INDEX = 1;
        Collections.shuffle(repo.getCards());
    }

    public List<Card> getAllCards() {
        return repo.getCards();
    }

    public List<Player> getAllPlayers() {
        return repo.getPlayers();
    }

    /*Not implemented yet*/
    public void checkingPTS() {
        for (Dice dice : repo.getDices()) {
            switch (dice.getState()) {
                case "Gold": ;
                    break;
                case "Diamond": ;
                    break;
            }
        }
    }

}
