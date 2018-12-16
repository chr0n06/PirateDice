/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAL.Repository;
import Model.Card;
import Model.Turn;
import Settings.Preferences;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author chr0n06
 */
public class Card_Controller {

    public Repository repo;
    private static Card_Controller card_services = null;

    private Card_Controller() {
        repo = Repository.getInstance();
    }

    public static Card_Controller getInstance() {
        if (card_services == null) {
            card_services = new Card_Controller();
        }
        return card_services;
    }

    public void pickACard(Turn turn) {
        if (Preferences.CARD_PACK_INDEX <= Preferences.CARD_PACK_QTY) {
            turn.setCard(getAllCards().get(Preferences.CARD_PACK_INDEX));
            Preferences.CARD_PACK_INDEX++;
        } else {
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
}
