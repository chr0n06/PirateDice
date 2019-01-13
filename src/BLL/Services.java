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

/**
 *
 * @author Maxime
 */
public class Services {

    public static Repository repo = Repository.getInstance();

    public static void rollAllDices() {
        repo.getTurn().setInitiated(true);
        for (Dice dice : repo.getDices()) {
            dice.rollDice();

            //remove lifes on the start
            if (dice.getState().equals("Death")) {
                repo.getTurn().setLifes(
                        repo.getTurn().getLifes() - 1
                );
                System.out.println("minus 1 from start, actual life = " + repo.getTurn().getLifes());
            }//if
        }//for
    }

    public static void acceptPoints() {
        int tempPoints = repo.getTurn().getPlayer().getPoint();
        Map<String, Integer> diceRepetions = calculateDiceCombo();
        repo.getTurn().getPlayer().setPoint(
                tempPoints + getTempPoints()//Add Turn point to actual points
        );

        //Card influence PirateBoatCardEasy
        if (repo.getTurn().getCard().getName().equals("PirateBoatCardEasy")) {
            if (diceRepetions.containsKey("Swords")) {
                if (diceRepetions.get("Swords") >= 2) {
                    repo.getTurn().getPlayer().setPoint(repo.getTurn().getPlayer().getPoint() + 300);
                    System.out.println("Player has killed the easy boat!");
                }
            }

        }
        //Card influence PirateBoatCardMedium
        if (repo.getTurn().getCard().getName().equals("PirateBoatCardMedium")) {
            if (diceRepetions.containsKey("Swords")) {
                if (diceRepetions.get("Swords") >= 3) {
                    repo.getTurn().getPlayer().setPoint(repo.getTurn().getPlayer().getPoint() + 500);
                    System.out.println("Player has killed the medium boat!");
                }
            }

        }
        //Card influence PirateBoatCardHard
        if (repo.getTurn().getCard().getName().equals("PirateBoatCardHard")) {
            if (diceRepetions.containsKey("Swords")) {
                if (diceRepetions.get("Swords") >= 4) {
                    repo.getTurn().getPlayer().setPoint(repo.getTurn().getPlayer().getPoint() + 1000);
                    System.out.println("Player has killed the hard boat!");
                }
            }
        }

    }

    public static int getTempPoints() {
        int points = 0;
        if ((repo.getTurn().getLifes() > 0) && (repo.getTurn().getLifes() <= 3)) {
            points += firstPhase(); //Count simple valuable dice
            points += secondPhase();//Count combo of similar dices
            points = thirdPhase(points); //Card influence PirateCard
            repo.getTurn().setScore(points);
        } else if (repo.getTurn().getLifes() <= 0) {
            repo.getTurn().setScore(0);
        }
        return repo.getTurn().getScore();
    }

    private static int firstPhase() {
        int tempPoints = 0;
        for (Dice dice : repo.getDices()) {
            if ((dice.getState().equals("Gold")) || (dice.getState().equals("Diamond"))) {
                tempPoints += Preferences.DICE_UNIT_COUNT;
            }
        }

        //Card influence GoldenPiece & DiamondCard
        if (repo.getTurn().getCard().getName().equals("GoldenPiece") || repo.getTurn().getCard().getName().equals("DiamondCard")) {
            tempPoints += Preferences.DICE_UNIT_COUNT;
        }//if

        return tempPoints;
    }

    private static int secondPhase() {
        int tempPoints = 0;
        Map<String, Integer> diceRepetions = calculateDiceCombo();

        for (Map.Entry<String, Integer> entry : diceRepetions.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            switch (value) {
                case 3:
                    tempPoints += Preferences.DICE_COMBO_3SIMILAR_COUNT;
                    break;
                case 4:
                    tempPoints += Preferences.DICE_COMBO_4SIMILAR_COUNT;
                    break;
                case 5:
                    tempPoints += Preferences.DICE_COMBO_5SIMILAR_COUNT;
                    break;
                case 6:
                    tempPoints += Preferences.DICE_COMBO_6SIMILAR_COUNT;
                    break;
                case 7:
                    tempPoints += Preferences.DICE_COMBO_7SIMILAR_COUNT;
                    break;
                case 8:
                    tempPoints += Preferences.DICE_COMBO_ALL_COUNT;
                    break;
            }//switch
        }//foreach
        System.out.println("Combo pts = " + tempPoints);
        return tempPoints;
    }

    private static int thirdPhase(int points) {
        if (repo.getTurn().getCard() != null) {
            if (repo.getTurn().getCard().getName().equals("PirateCard")) {
                return points *= 2;
            }
        }
        return points;
    }

    private static Map<String, Integer> calculateDiceCombo() {
        Map<String, Integer> diceRepetions = new HashMap<String, Integer>();

        for (Dice dice : repo.getDices()) {
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

        //Card influence GoldenPiece
        if (repo.getTurn().getCard().getName().equals("GoldenPiece")) {
            diceRepetions.merge("Gold", 1, Integer::sum);
        }//if
        //Card influence DiamondCard
        if (repo.getTurn().getCard().getName().equals("DiamondCard")) {
            diceRepetions.merge("Diamond", 1, Integer::sum);
        }//if
        return diceRepetions;
    }

    public static void rollSpecificDices(List<Integer> dices) {
        if (!dices.isEmpty()) {
            if (repo.getTurn().getLifes() > 0) {
                for (Integer diceId : dices) {
                    if (isDiceRollable(diceId)) {
                        repo.getDices().get(diceId - 1).rollDice();
                        if (repo.getDices().get(diceId - 1).getState().equals("Death")) {
                            repo.getTurn().setLifes(
                                    repo.getTurn().getLifes() - 1
                            );
                            System.out.println("minus 1, actual life = " + repo.getTurn().getLifes());
                        }//if 
                    }//if
                }//for
            } else if (repo.getTurn().getLifes() == 0) {
                System.out.println("You're death!!");
            } else if (repo.getTurn().getLifes() < 0) {
                System.out.println("You beat the death");
                //Implement a method to go on the death island
                boolean isNewDiceDeath = false;

                for (Integer diceId : dices) {
                    if (isDiceRollable(diceId)) {
                        repo.getDices().get(diceId - 1).rollDice();
                        if (repo.getDices().get(diceId - 1).getState().equals("Death")) {
                            repo.getTurn().setLifes(
                                    repo.getTurn().getLifes() - 1
                            );
                            System.out.println("minus 1, actual life = " + repo.getTurn().getLifes());
                        }//if 
                    }//if
                }//for   
            }
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
        resetTurnLife();

        //Card influence PirateBoatCardEasy
        if (repo.getTurn().getCard().getName().equals("PirateBoatCardEasy")) {
            if (repo.getTurn().getPlayer().getPoint() >= 300) {
                repo.getTurn().getPlayer().setPoint(repo.getTurn().getPlayer().getPoint() - 300);
            } else {
                repo.getTurn().getPlayer().setPoint(0);
            }//else
            System.out.println("Minus 300 points till you roll at least 2 swords");
        }//if
        
        //Card influence PirateBoatCardMedium
        if (repo.getTurn().getCard().getName().equals("PirateBoatCardMedium")) {
            if (repo.getTurn().getPlayer().getPoint() >= 500) {
                repo.getTurn().getPlayer().setPoint(repo.getTurn().getPlayer().getPoint() - 500);
            } else {
                repo.getTurn().getPlayer().setPoint(0);
            }//else
            System.out.println("Minus 500 points till you roll at least 3 swords");
        }//if
        
        //Card influence PirateBoatCardHard
        if (repo.getTurn().getCard().getName().equals("PirateBoatCardHard")) {
            if (repo.getTurn().getPlayer().getPoint() >= 1000) {
                repo.getTurn().getPlayer().setPoint(repo.getTurn().getPlayer().getPoint() - 1000);
            } else {
                repo.getTurn().getPlayer().setPoint(0);
            }//else
            System.out.println("Minus 1000 points till you roll at least 4 swords");
        }//if

        //Card influence SimpleSkullCard
        if (repo.getTurn().getCard().getName().equals("SimpleSkullCard")) {
            repo.getTurn().setLifes(repo.getTurn().getLifes() - 1);
            System.out.println("Minus 1 life because of the SimpleSkullCard");
        }
        //Card influence DoubleSkullCard
        if (repo.getTurn().getCard().getName().equals("DoubleSkullCard")) {
            repo.getTurn().setLifes(repo.getTurn().getLifes() - 2);
            System.out.println("Minus 2 life because of the DoubleSkullCard");
        }

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

    public static void resetTurnLife() {
        repo.getTurn().setLifes(Preferences.DEFAULT_LIFE_QTY);
        repo.getTurn().setInitiated(false);
        System.out.println("lifes of that turn as been resetted");
    }

    private static void resetCardPackIndex() {
        Preferences.CARD_PACK_INDEX = 0;
        Collections.shuffle(repo.getCards());
    }

    public static Turn getTurn() {
        return repo.getTurn();
    }

}
