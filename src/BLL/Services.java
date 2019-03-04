/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Repository;
import Logger.LogManager;
import Model.Dice;
import Model.Player;
import Model.Turn;
import Settings.Preferences;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author Maxime
 */
public class Services {

    /**
     * LOGGER
     */
    private static Logger logger = Logger.getLogger(Preferences.class.getName());

    public static Repository repo = Repository.getInstance();

    /**
     * Roll Dices methods
     */
    /**
     * The rollAllDices method allow to roll every dices for the first time in
     * the turn.
     *
     * @param none
     * @return void
     * @version 1.0
     *
     * @author Maxime Laniel
     */
    public static void rollAllDices() {
        //repo.getTurn().setInitiated(true);
        for (Dice dice : repo.getDices()) {
            dice.rollDice();
            //remove lifes on the start
            if (dice.getState().equals("Death")) {
                repo.getTurn().setLifes(
                        repo.getTurn().getLifes() - 1
                );
                logger.log(Level.INFO, "Minus 1 from start, actual life = " + repo.getTurn().getLifes());
            }//if
        }//for
    }//rollAllDices

    /**
     * The rollSpecificDices method allow to roll every dices that have been
     * checked.
     *
     * @param List<Integer>
     * @return void
     * @version 1.0
     *
     * @author Maxime Laniel
     */
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
                            logger.info("Minus 1, actual life = " + repo.getTurn().getLifes());
                        }//if 
                    }//if
                }//for
            }//if
            //Card influence ChestCard
            /* if(repo.getTurn().getCard().getName().equals("ChestCard") && ){
                
            }*/
        }//if
    }//rollSpecificDices

    /**
     * The rollAllDicesFromDeathIsland method allow to roll every dices when the
     * player enter into the Death Island.
     *
     * @param none
     * @return void
     * @version 1.0
     *
     * @author Maxime Laniel
     */
    public static void rollAllDicesFromDeathIsland() {
        if (repo.getTurn().isMinusLife()) {
            repo.getTurn().setMinusLife(false);
            for (Dice dice : repo.getDices()) {
                if (!dice.getState().equals("Death")) {
                    dice.rollDice();
                    if (dice.getState().equals("Death")) {
                        repo.getTurn().setLifes(
                                repo.getTurn().getLifes() - 1);//remove lifes in DeathIsland
                        logger.log(Level.INFO, "Minus 1 from Death Island, actual life = " + repo.getTurn().getLifes());
                        repo.getTurn().setMinusLife(true);
                    }//if
                }//if
            }//for
        }//if  
    }

    /**
     * Points manager methods
     */
    /**
     * The acceptPoints method allows the player to accept points of the actual
     * turn
     *
     * @param none
     * @return void
     * @version 1.0
     *
     * @author Maxime Laniel
     */
    public static void acceptPoints() {
        int tempPoints = repo.getTurn().getPlayer().getPoint();

        if (!repo.getTurn().isPointsAccepted()) {
            repo.getTurn().setPointsAccepted(true);
            repo.getTurn().getPlayer().setPoint(
                    tempPoints + getTempPoints()//Add Turn point to actual points
            );
        }
    }

    /**
     * The getTempPoints method go trought multiple phases to check after each
     * roll what is the value of the dices
     *
     * @param none
     * @return Integer
     * @version 1.0
     *
     * @author Maxime Laniel
     */
    public static int getTempPoints() {
        int points = 0;
        Map<String, Integer> diceRepetions = calculateDiceCombo();

        if ((repo.getTurn().getLifes() > 0) && (repo.getTurn().getLifes() <= 3)) {
            points += firstPhase(); //Count simple valuable dice
            points += secondPhase();//Count combo of similar dices
            points = thirdPhase(points); //Card influence PirateCard

            //Card influence PirateBoat
            if ((repo.getTurn().getCard().getName().equals(Preferences.CARD_PIRATEBOATEASY_NAME))
                    || (repo.getTurn().getCard().getName().equals(Preferences.CARD_PIRATEBOATMEDIUM_NAME))
                    || (repo.getTurn().getCard().getName().equals(Preferences.CARD_PIRATEBOATHARD_NAME))) {
                int pirateBoatInfluence = pirateBoatInfluence(diceRepetions);
                if (pirateBoatInfluence < 0) {
                    points = pirateBoatInfluence;
                } else {
                    points += pirateBoatInfluence;
                }
            }

            repo.getTurn().setScore(points);

        } else if (repo.getTurn().getLifes() <= 0) {
            //Card influence ChestCard
            if (repo.getTurn().getCard().getName().equals("ChestCard")) {
                //Each card that has been checked
            }
            //Card influence PirateBoat when Player is death
            if ((repo.getTurn().getCard().getName().equals(Preferences.CARD_PIRATEBOATEASY_NAME))
                    || (repo.getTurn().getCard().getName().equals(Preferences.CARD_PIRATEBOATMEDIUM_NAME))
                    || (repo.getTurn().getCard().getName().equals(Preferences.CARD_PIRATEBOATHARD_NAME))) {
                int pirateBoatInfluence = pirateBoatInfluence(diceRepetions);
                if (pirateBoatInfluence < 0) {
                    repo.getTurn().setScore(pirateBoatInfluence);
                }
            } else {
                repo.getTurn().setScore(0);
            }
        }
        return repo.getTurn().getScore();
    }

    /**
     * The firstPhase method count all diamond & gold dices
     *
     * @param none
     * @return Integer
     * @version 1.0
     *
     * @author Maxime Laniel
     */
    private static int firstPhase() {
        int tempPoints = 0;

        //Count Gold an Diamond dice on this turn
        for (Dice dice : repo.getDices()) {
            if ((dice.getState().equals(Preferences.DICE_GOLD_NAME)) || (dice.getState().equals(Preferences.DICE_DIAMOND_NAME))) {
                tempPoints += Preferences.DICE_UNIT_COUNT;
            }
        }

        //Card influence GoldenPiece & DiamondCard
        if (repo.getTurn().getCard().getName().equals(Preferences.CARD_PIECEOFGOLD_NAME) || repo.getTurn().getCard().getName().equals(Preferences.CARD_DIAMOND_NAME)) {
            tempPoints += Preferences.DICE_UNIT_COUNT;
        }//if

        return tempPoints;
    }

    /**
     * The secondPhase method count all dices combo
     *
     * @param none
     * @return Integer
     * @version 1.0
     *
     * @author Maxime Laniel
     */
    private static int secondPhase() {
        int tempPoints = 0;
        Map<String, Integer> diceRepetions = calculateDiceCombo();

        for (Map.Entry<String, Integer> entry : diceRepetions.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (!key.equals(Preferences.DICE_DEATH_NAME)) {
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
        }

        //End turn bonus checker
        tempPoints += endTurnBonusChecker(diceRepetions);

        logger.log(Level.INFO, "Combo pts = " + tempPoints);
        return tempPoints;
    }

    /**
     * The thirdPhase method check if we have to double the amount of points
     *
     * @param none
     * @return Integer
     * @version 1.0
     *
     * @author Maxime Laniel
     */
    private static int thirdPhase(int points) {
        if (repo.getTurn().getCard() != null) {
            if (repo.getTurn().getCard().getName().equals("PirateCard")) {
                return points *= 2;
            }
        }
        return points;
    }

    /**
     * The calculateDiceCombo method give the number of repetition(s) for each
     * dice in the actual turn.
     *
     * @param none
     * @return Map<String, Integer>
     * @version 1.0
     *
     * @author Maxime Laniel
     */
    private static Map<String, Integer> calculateDiceCombo() {
        Map<String, Integer> diceRepetions = new HashMap<String, Integer>();

        for (Dice dice : repo.getDices()) {
            switch (dice.getState()) {
                case Preferences.DICE_GOLD_NAME:
                    diceRepetions.merge(Preferences.DICE_GOLD_NAME, 1, Integer::sum);
                    break;
                case Preferences.DICE_DIAMOND_NAME:
                    diceRepetions.merge(Preferences.DICE_DIAMOND_NAME, 1, Integer::sum);
                    break;
                case Preferences.DICE_PARROT_NAME:
                    diceRepetions.merge(Preferences.DICE_PARROT_NAME, 1, Integer::sum);
                    break;
                case Preferences.DICE_MONKEY_NAME:
                    diceRepetions.merge(Preferences.DICE_MONKEY_NAME, 1, Integer::sum);
                    break;
                case Preferences.DICE_SWORDS_NAME:
                    diceRepetions.merge(Preferences.DICE_SWORDS_NAME, 1, Integer::sum);
                    break;
                case Preferences.DICE_DEATH_NAME:
                    diceRepetions.merge(Preferences.DICE_DEATH_NAME, 1, Integer::sum);
                    break;
            }//switch
        }//for

        //Card influence MonkeyPirateCard
        if (repo.getTurn().getCard().getName().equals("MonkeyParrotCard")) {
            if (diceRepetions.containsKey("Monkey") && diceRepetions.containsKey("Parrot")) {
                int monkeyParrotAggregation = 0;
                monkeyParrotAggregation = diceRepetions.get("Monkey");
                diceRepetions.replace("Parrot", diceRepetions.get("Parrot"), diceRepetions.get("Parrot") + monkeyParrotAggregation);
                diceRepetions.remove("Monkey");
            }
        }

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

    /**
     * The isDiceRollable check if the selected Dice is rollable (dead or not)
     *
     * @param Integer
     * @return boolean
     * @version 1.0
     *
     * @author Maxime Laniel
     */
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
        deathIslandInfluence();
        selectAPlayer(); //Inject a player and a card to the new turn
        pickACard();
        resetTurnLife();
        resetTurnMinusLife(); //Needed when a player goes on the Death Island
        resetTurnInitiation();
        resetPointsAccepted();
        skullCardInfluence();
    }

    public static void selectAPlayer() {
        logger.info("Player #" + Preferences.PLAYER_TURN_INDEX + " is actually playing! Player quantity = " + Services.getAllPlayers().size());
        if (Preferences.PLAYER_TURN_INDEX < Services.getAllPlayers().size()) {
            repo.getTurn().setPlayer(repo.getPlayers().get(Preferences.PLAYER_TURN_INDEX++));
        } else {
            resetPlayerIndex();
            logger.info("Player index has been resetted");
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
            logger.log(Level.INFO, "Card Pack has been resetted");
            nextTurn();
        }
    }

    public static void resetTurnLife() {
        repo.getTurn().setLifes(Preferences.DEFAULT_LIFE_QTY);
        //repo.getTurn().setInitiated(false);
        logger.log(Level.INFO, "Lifes of that turn as been resetted");
    }

    public static void resetTurnMinusLife() {
        repo.getTurn().setMinusLife(true);
        logger.log(Level.INFO, "minusLife of that turn as been resetted");
    }

    public static void resetTurnInitiation() {
        repo.getTurn().setInitiated(false);
        logger.log(Level.INFO, "Initiation of that turn as been resetted");
    }

    private static void resetCardPackIndex() {
        Preferences.CARD_PACK_INDEX = 0;
        Collections.shuffle(repo.getCards());
    }

    private static void resetPointsAccepted() {
        repo.getTurn().setPointsAccepted(false);
        logger.log(Level.INFO, "PointsAccepted of that turn as been resetted");
    }

    public static Turn getTurn() {
        return repo.getTurn();
    }

    public static boolean isOneDeathDice() {
        boolean status = false;
        for (Dice dice : repo.getDices()) {
            if (dice.isDeath()) {
                status = true;
                return status;
            }
        }
        return status;
    }

    public static int findFirstDeathDice() {
        int index = 0;//can cause error 

        for (Dice dice : repo.getDices()) {
            if (dice.isDeath()) {
                return dice.getId() - 1;
            }
        }
        return index;
    }

    public static boolean witchCardInfluence() {
        return Services.getTurn().getCard().getName().equals(Preferences.CARD_WITCH_NAME);
    }

    public static boolean actualCard(String cardName) {
        return repo.getTurn().getCard().getName().equals(cardName);
    }

    public static boolean actualDice(String diceName) {
        return true;
    }

    public static void skullCardInfluence() {
        int lifeRemoved = 0;

        switch (repo.getTurn().getCard().getName()) {
            case Preferences.CARD_SIMPLESKULL_NAME:
                lifeRemoved = 1;
                break;
            case Preferences.CARD_DOUBLESKULL_NAME:
                lifeRemoved = 2;
                break;
        }

        repo.getTurn().setLifes(repo.getTurn().getLifes() - lifeRemoved);
        logger.info(lifeRemoved + " life remove on start caused by a skull card");
    }

    public static int pirateBoatInfluence(Map<String, Integer> diceRepetions) {
        switch (repo.getTurn().getCard().getName()) {
            case Preferences.CARD_PIRATEBOATEASY_NAME:
                return pirateBoatPointManager(diceRepetions, 300, 2);
            case Preferences.CARD_PIRATEBOATMEDIUM_NAME:
                return pirateBoatPointManager(diceRepetions, 500, 3);
            case Preferences.CARD_PIRATEBOATHARD_NAME:
                return pirateBoatPointManager(diceRepetions, 1000, 4);
        }
        logger.severe("PirateBoatInfluence doesn't work");
        return 0;
    }

    public static int pirateBoatPointManager(Map<String, Integer> diceRepetions, int point, int swordQty) {
        if (repo.getTurn().getLifes() < 0 && !repo.getTurn().isInitiated()) {
            logger.info("Pirate Boat Card doesn't make any effects because the player goes on the death island !");
            return 0;
        } else {
            if (diceRepetions.containsKey(Preferences.DICE_SWORDS_NAME)) {
                if (diceRepetions.get(Preferences.DICE_SWORDS_NAME) < swordQty) {
                    logger.info("Player lost " + point + " pts cause the pirate boat won!");
                    return Math.negateExact(point);
                } else {
                    logger.info("Player won " + point + " pts cause the pirate boat lost!");
                    return point;
                }//if3
            } else {
                logger.info("Player lost " + point + " pts cause he doesn''t have any sword!");
                return Math.negateExact(point);
            }//if2
        }//if1
    }//pirateBoatPointManager

    public static void deathIslandInfluence() {
        if (repo.getTurn().getLifes() < 0) {
            for (Player player : repo.getPlayers()) {
                //If actual card is Pirate Card, player loose the double
                if (player.getId() != repo.getTurn().getPlayer().getId()) {
                    if (repo.getTurn().getCard().getName().equals(Preferences.CARD_PIRATE_NAME)) {
                        logger.info("Player: " + player.getName()
                                + " has " + player.getPoint()
                                + "! This turn he lost + " + Math.abs(repo.getTurn().getLifes() - 3) * 200
                                + " he now turned to " + (player.getPoint() - Math.abs(repo.getTurn().getLifes() - 3) * 200)
                                + " point(s)!");
                        player.setPoint(player.getPoint() - Math.abs(repo.getTurn().getLifes() - 3) * 200);
                    }
                    //Each player on the bench lost 100 pts for each Skull the actual player roll. -3 because the player start with 3 pts. 
                    logger.info("Player: " + player.getName()
                            + " has " + player.getPoint()
                            + "! This turn he lost + " + Math.abs(repo.getTurn().getLifes() - 3) * 100
                            + " he now turned to " + (player.getPoint() - Math.abs(repo.getTurn().getLifes() - 3) * 100)
                            + " point(s)!");
                    player.setPoint(player.getPoint() - Math.abs(repo.getTurn().getLifes() - 3) * 100);

                }
            }
        }
    }

    public static String endGameManager() {
        Player player = repo.getTurn().getPlayer();

        if (player != null) {
            if (player.getPoint() >= Preferences.WINNING_SCORE) {
                System.out.println("player beat the winning score");
                if (repo.getTurn().isEndGameActivated()) {

                    if (repo.getTurn().getPlayerWhoActivatedEndGame().getId() == player.getId()) {

                        for (Player actualplayer : repo.getPlayers()) {
                            if (actualplayer.getPoint() > player.getPoint()) {
                                player = actualplayer;
                            }
                        }
                        return player.getName() + " won the game with a total of " + player.getPoint() + " points !!!";
                    }
                }
                repo.getTurn().setEndGameActivated(true);
                System.out.println("End Game is activated");
                repo.getTurn().setPlayerWhoActivatedEndGame(player);
                System.out.println("Player " + repo.getTurn().getPlayerWhoActivatedEndGame().getName() + " has activated the end game");
            }
        }
        return null;
    }

    public static int endTurnBonusChecker(Map<String, Integer> diceRepetions) {
        for (Map.Entry<String, Integer> entry : diceRepetions.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            if (key.equals(Preferences.DICE_DEATH_NAME)) {
                logger.warning("At least one dead dice!");
                return 0;
            } else if (key.equals(Preferences.DICE_MONKEY_NAME)
                    || key.equals(Preferences.DICE_PARROT_NAME)
                    || key.equals(Preferences.DICE_SWORDS_NAME)) {
                if (value < 3) {
                    logger.warning(key + " has " + value + " repetitions!");
                    return 0;
                }//if2
            }//if1
        }//for
        logger.info("Player get the ending turn bonus because all dices on the table counts!!!");
        return Preferences.DICE_COMBO_ALL_COUNT;
    }//endTurnBonusChecker

}
