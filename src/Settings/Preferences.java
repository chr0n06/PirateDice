/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

/**
 *
 * @author Maxime
 */
public class Preferences {

    /*CARD SETUP*/ 
    
    /**
     * NAME
     */
    
    /**
     * QTY
     */
    public static int CARD_PACK_INDEX = 0;
    public static int CARD_PACK_QTY = 35;
    public static int CARD_PIRATEBOATEASY_QTY = 2;
    public static int CARD_PIRATEBOATMEDIUM_QTY = 2;
    public static int CARD_PIRATEBOATHARD_QTY = 2;
    public static int CARD_DOUBLESKULL_QTY = 2;
    public static int CARD_SIMPLESKULL_QTY = 3;
    public static int CARD_CHEST_QTY = 4;
    public static int CARD_PIRATE_QTY = 4;
    public static int CARD_BRILLIANT_DIAMOND_QTY = 4;
    public static int CARD_PIECEOFGOLD_QTY = 4;
    public static int CARD_MONKEY_PIRATE_QTY = 4;
    public static int CARD_WITCH_QTY = 4;
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
    public static String CARD_BRILLIANT_DIAMOND_IMG = "Assets/Cards/Diamond_x.png";
    public static String CARD_PIECEOFGOLD_IMG = "Assets/Cards/GoldenPiece_x.png";
    public static String CARD_MONKEY_PIRATE_IMG = "Assets/Cards/MonkeyParrot_x.png";
    public static String CARD_WITCH_IMG = "Assets/Cards/witchCard_x.png";

    /*DICE STEP*/
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
    public static int WINNING_SCORE = 6000;

}
