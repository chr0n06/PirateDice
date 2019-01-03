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

    public static void rollAllDices() {
        for (Dice dice : repo.getDices()) {
            dice.rollDice();
        }
    }

    public static int getTempPoints() {
        int points = 0;
        points += firstPhase(); // Count simple valuable dice
        points += secondPhase();// Count combo of similar dices
        repo.getTurn().setScore(points); 
        return repo.getTurn().getScore();
    }
    
    private static int firstPhase(){
        int tempPoints = 0;
        for (Dice dice : repo.getDices()) {
            if ((dice.getState().equals("Gold")) || (dice.getState().equals("Diamond"))){
                tempPoints += Preferences.DICE_UNIT_COUNT;
            }
        }
        return tempPoints;
    }
    
     private static int secondPhase(){
        int tempPoints = 0;
        for (Dice dice : repo.getDices()) {
            
        }
        return tempPoints;
    }
    
    public static void rollSpecificDices(List<Integer> dices) {
        if (!dices.isEmpty()) {
            for (Integer diceId : dices) {
                if (isDiceRollable(diceId)) {
                    repo.getDices().get(diceId - 1).rollDice();
                }//if
            }//for
        }//if
    }//rollSpecificDices

    public static boolean isDiceRollable(int diceId) {
        return !repo.getDices().get(diceId - 1).isDeath();
    }

    public static List<Dice> getAllDices() {
        return repo.getDices();
    }

    public static List<Player> getAllPlayers() {
        return repo.getPlayers();
    }

    public static void nextTurn() {
        //Inject a player and a card to the new turn
        selectAPlayer();
        pickACard();


    }
    
  

    public static void selectAPlayer() {
        System.out.println(Preferences.PLAYER_TURN_INDEX + "--" + Services.getAllPlayers().size());
        if (Preferences.PLAYER_TURN_INDEX < Services.getAllPlayers().size()) {
            repo.getTurn().setPlayer(repo.getPlayers().get(Preferences.PLAYER_TURN_INDEX++));
        } else {
            resetPlayerIndex();
            System.out.println("Player index has been resetted");
            nextTurn();
        }
    }

    private static void resetPlayerIndex() {
        Preferences.PLAYER_TURN_INDEX = 0;
    }

    public static void pickACard() {
        if (Preferences.CARD_PACK_INDEX < Preferences.CARD_PACK_QTY) {
            repo.getTurn().setCard(repo.getCards().get(Preferences.CARD_PACK_INDEX++));
        } else {
            resetCardPackIndex();
            System.out.println("Card Pack has been resetted");
            nextTurn();
        }
    }

    private static void resetCardPackIndex() {
        Preferences.CARD_PACK_INDEX = 0;
        Collections.shuffle(repo.getCards());
    }

    public static Turn getTurn() {
        return repo.getTurn();
    }

}
