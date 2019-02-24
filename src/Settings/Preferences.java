/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

import Logger.LogManager;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime
 */
public class Preferences {
    /*LOGGER*/
    public static final Level LOGGER_LEVEL = Level.ALL;
    private static Logger logger = LogManager.setLogger(
            Logger.getLogger(Preferences.class.getName())
    );
    
    /**
     * ANSI COLOR
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /*CARD SETUP*/
    /**
     * NAME
     */
    public static final String CARD_PIRATEBOATEASY_NAME = "PirateBoatCardEasy";
    public static final String CARD_PIRATEBOATMEDIUM_NAME = "PirateBoatCardMedium";
    public static final String CARD_PIRATEBOATHARD_NAME = "PirateBoatCardHard";
    public static final String CARD_DOUBLESKULL_NAME = "DoubleSkullCard";
    public static final String CARD_SIMPLESKULL_NAME = "SimpleSkullCard";
    public static final String CARD_CHEST_NAME = "ChestCard";
    public static final String CARD_PIRATE_NAME = "PirateCard";
    public static final String CARD_DIAMOND_NAME = "DiamondCard";
    public static final String CARD_PIECEOFGOLD_NAME = "GoldenPiece";
    public static final String CARD_MONKEY_PIRATE_NAME = "MonkeyParrotCard";
    public static final String CARD_WITCH_NAME = "WitchCard";

    /**
     * QTY
     */
    public static int CARD_PACK_INDEX = 0;
    public static int CARD_PIRATEBOATEASY_QTY = 2;
    public static int CARD_PIRATEBOATMEDIUM_QTY = 2;
    public static int CARD_PIRATEBOATHARD_QTY = 2;
    public static int CARD_DOUBLESKULL_QTY = 2;
    public static int CARD_SIMPLESKULL_QTY = 3;
    public static int CARD_CHEST_QTY = 49;
    public static int CARD_PIRATE_QTY = 4;
    public static int CARD_DIAMOND_QTY = 4;
    public static int CARD_PIECEOFGOLD_QTY = 4;
    public static int CARD_MONKEY_PIRATE_QTY = 4;
    public static int CARD_WITCH_QTY = 4;
    public static int CARD_PACK_QTY = cardCounter();
    /**
     * PATH
     */
    public static String CARD_PIRATEBOATEASY_IMG = "Assets/Cards/PirateBoatEasyCard_x.png";
    public static String CARD_PIRATEBOATMEDIUM_IMG = "Assets/Cards/PirateBoatMediumCard_x.png";
    public static String CARD_PIRATEBOATHARD_IMG = "Assets/Cards/PirateBoatHardCard_x.png";
    public static String CARD_DOUBLESKULL_IMG = "Assets/Cards/DoubleSkullCard_x.png";
    public static String CARD_SIMPLESKULL_IMG = "Assets/Cards/SimpleSkullCard_x.png";
    public static String CARD_CHEST_IMG = "Assets/Cards/ChestCard_x.png";
    public static String CARD_PIRATE_IMG = "Assets/Cards/PirateCard_x.png";
    public static String CARD_DIAMOND_IMG = "Assets/Cards/Diamond_x.png";
    public static String CARD_PIECEOFGOLD_IMG = "Assets/Cards/GoldenPiece_x.png";
    public static String CARD_MONKEY_PIRATE_IMG = "Assets/Cards/MonkeyParrot_x.png";
    public static String CARD_WITCH_IMG = "Assets/Cards/witchCard_x.png";

    /*DICE STEP*/
    /**
     * NAME
     */
    public static final String DICE_DEATH_NAME = "Death";
    public static final String DICE_SWORDS_NAME = "Swords";
    public static final String DICE_PARROT_NAME = "Parrot";
    public static final String DICE_MONKEY_NAME = "Monkey";
    public static final String DICE_GOLD_NAME = "Gold";
    public static final String DICE_DIAMOND_NAME = "Diamond";

    /**
     * PTS
     */
    public static int DICE_SIDE_QTY = 6;
    public static int DICE_UNIT_COUNT = 100;
    public static int DICE_COMBO_3SIMILAR_COUNT = 100;
    public static int DICE_COMBO_4SIMILAR_COUNT = 200;
    public static int DICE_COMBO_5SIMILAR_COUNT = 500;
    public static int DICE_COMBO_6SIMILAR_COUNT = 1000;
    public static int DICE_COMBO_7SIMILAR_COUNT = 2000;
    public static int DICE_COMBO_8SIMILAR_COUNT = 4000;
    public static int DICE_COMBO_ALL_COUNT = 500;
    /**
     * LAYOUT
     */
    public static String DICE_LAYOUT = "3D";//Zeke"Cartoon";//"3D";////"Cartoon";"Real"; 

    /*PLAYER SETUP*/
    /**
     * LIST
     */
    public static String[] PLAYER_NAMES = {
        "Maxime",
        "Sebastien",
        "Cynthia",
        "Charles"
    };
    public static int PLAYER_STARTING_POINTS = 0;
    public static int PLAYER_TURN_INDEX = 0;

    /*TURN SETUP*/
    public static int DEFAULT_LIFE_QTY = 3;
    public static int WINNING_SCORE = 1000;

    /**
     * The cardcounter method count every card that has been named correctly to
     * be considered in the deck
     *
     * @param none
     * @return Integer
     * @version 1.0
     *
     * @author Maxime Laniel
     */
    private static int cardCounter() {
        int cardCounter = 0;
        Field[] fields = Preferences.class.getFields();

        for (Field field : fields) {
            //Here's the naming Convention to be considered in the cardCounter
            if (field.getName().contains("CARD") && field.getName().contains("QTY")) {
                try {
                    cardCounter += field.getInt(field);
                    //logger.log(Level.INFO, field.getName() + " contains " + field.getInt(field) + " cards !");
                } catch (IllegalArgumentException ex) {
                    logger.log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
        logger.log(Level.INFO, cardCounter + " card(s) will be created for the deck!");

        return cardCounter;
    }

}
