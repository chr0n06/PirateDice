/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Repository;
import Model.Dice;
import Model.Player;
import Model.Turn;
import Settings.Preferences;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

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

    private static int firstPhase() {
        int tempPoints = 0;
        for (Dice dice : repo.getDices()) {
            if ((dice.getState().equals("Gold")) || (dice.getState().equals("Diamond"))) {
                tempPoints += Preferences.DICE_UNIT_COUNT;
            }
        }
        return tempPoints;
    }

    private static int secondPhase() {
        //Count Dice Repetition
        int tempPoints = 0;
        Map<String, Integer> diceRepetions = new HashMap<String, Integer>() {
            {
                put("Gold", 0);
                put("Diamond", 0);
                put("Parrot", 0);
                put("Monkey", 0);
                put("Swords", 0);
            }
        };

        for (Dice dice : repo.getDices()) {
            System.out.println(repo.getDices().size());
            switch (dice.getState()) {
                case "Gold":
                    diceRepetions.merge("Gold", 1, Integer::sum);
                    break;
                case "Diamond":
                    diceRepetions.merge("Diamond", 1, Integer::sum);
                    break;
                case "Parrot":
                    diceRepetions.merge("Parrot", 1, Integer::sum);
                    break;
                case "Monkey":
                    diceRepetions.merge("Monkey", 1, Integer::sum);
                    break;
                case "Swords":
                    diceRepetions.merge("Swords", 1, Integer::sum);
                    break;
            }//switch
        }//for
        
        diceRepetions.forEach( (key, value) -> { System.out.println( "Key: " + key + "\t" + " Value: " + value ); });
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
