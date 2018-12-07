/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

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
public class Card_Services {

    public Repository repo;
    private static Card_Services card_services = null;

    private Card_Services() {
        repo = Repository.getInstance();
    }

    public static Card_Services getInstance() {
        if (card_services == null) {
            card_services = new Card_Services();
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
