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
 * @author Maxime
 */
public class Services {

    public static Repository repo = Repository.getInstance();


    public static Card pickACard() {
        if (Preferences.CARD_PACK_INDEX <= Preferences.CARD_PACK_QTY) {
            repo.getCards().get(Preferences.CARD_PACK_INDEX);
            Preferences.CARD_PACK_INDEX++;
        } else {
            resetCardPackIndex();
        }
        return null;
    }

    private static void resetCardPackIndex() {
        Preferences.CARD_PACK_INDEX = 1;
        Collections.shuffle(repo.getCards());
    }


    public static void rollAllDices() {
        for (Dice dice : repo.getDices()) {
            dice.rollDice();
        }
    }

    public static void rollSpecificDices(List<Integer> dices) {
        for (Integer diceId : dices) {
            if (isDiceRollable(diceId)) {
                repo.getDices().get(diceId - 1).rollDice();
            }

        }
    }

    public static boolean isDiceRollable(int diceId) {
        return !repo.getDices().get(diceId - 1).isDeath();
    }

    public static List<Dice> getAllDices() {
        return repo.getDices();
    }

     public static List<Player> getAllPlayers() {
        return repo.getPlayers();
    }
    
    public static void nextTurn(){
        //Inject a player and a card to the next turn
        //repo.getTurn().setPlayer(repo.getPlayers().get(Preferences.PLAYER_TURN_INDEX++));
        if(Preferences.CARD_PACK_INDEX < Preferences.CARD_PACK_QTY){
            repo.getTurn().setCard(repo.getCards().get(Preferences.CARD_PACK_INDEX++));
        } else {
            resetCardPackIndex();
            System.out.println("Pack has been resetted");
            nextTurn();
        }
        
        

    }
    
    public static Turn getTurn(){
        return repo.getTurn();
    }
     
}
