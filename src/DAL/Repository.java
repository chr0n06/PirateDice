package DAL;

import Logger.LogManager;
import Model.Card;
import Model.Dice;
import Model.Player;
import Model.Turn;
import Settings.Preferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chr0n06
 */
public class Repository {

    /**
     * LOGGER
     */
    private static Logger logger =  Logger.getLogger(Preferences.class.getName());
    
    private static Repository repository = null;
    
    private Turn turn;
    private List<Dice> dices;
    private List<Player> players;
    private List<Card> cards;

    // private constructor restricted to this class itself 
    private Repository() {
        this.dices = new ArrayList<>();
        this.players = new ArrayList<>();
        this.cards = new ArrayList<>();
        this.turn = new Turn();
        initializeBoard();
    }

    public static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    private void initializeBoard() {
        generateDices();
        generatePlayers();
        generateCards();
    }

    
    private void generateDices() {
        for (int i = 0; i < 8; i++) {
            dices.add(new Dice());
        }
    }

    private void generatePlayers() {
        for (int i = 0; i < Preferences.PLAYER_NAMES.length; i++) {
            players.add(new Player(Preferences.PLAYER_NAMES[i]));
        }
        Collections.shuffle(players);
    }

    private void generateCards() {
        cardQtyManager(
                Preferences.CARD_WITCH_NAME, 
                "You have the capability to reroll one Death dice by the end of the turn!",
                Preferences.CARD_WITCH_IMG,
                Preferences.CARD_WITCH_QTY
        );
        cardQtyManager(
                Preferences.CARD_SIMPLESKULL_NAME,
                "Start your turn with only 2 lifes",
                Preferences.CARD_SIMPLESKULL_IMG,
                Preferences.CARD_SIMPLESKULL_QTY
        );
        cardQtyManager(
                Preferences.CARD_DOUBLESKULL_NAME,
                "Start your turn with only 1 life",
                Preferences.CARD_DOUBLESKULL_IMG,
                Preferences.CARD_DOUBLESKULL_QTY
        );
        cardQtyManager(
                Preferences.CARD_PIRATE_NAME,
                "Your points will be doubled at the end of the turn", 
                Preferences.CARD_PIRATE_IMG,
                Preferences.CARD_PIRATE_QTY
        );
        cardQtyManager(
                Preferences.CARD_MONKEY_PIRATE_NAME,
                "Monkey and Parrot are considered similars dices", 
                Preferences.CARD_MONKEY_PIRATE_IMG,
                Preferences.CARD_MONKEY_PIRATE_QTY
        );
        cardQtyManager(
                Preferences.CARD_PIRATEBOATEASY_NAME,
                "Roll 2 swords dices to destroy the pirate boat and collecting his treasure or die trying and lose the equivalent", 
                Preferences.CARD_PIRATEBOATEASY_IMG,
                Preferences.CARD_PIRATEBOATEASY_QTY
        );
        cardQtyManager(
                Preferences.CARD_PIRATEBOATMEDIUM_NAME,
                "Roll 3 swords dices to destroy the pirate boat and collecting his treasure or die trying and lose the equivalent", 
                Preferences.CARD_PIRATEBOATMEDIUM_IMG,
                Preferences.CARD_PIRATEBOATMEDIUM_QTY
        );
        cardQtyManager(
                Preferences.CARD_PIRATEBOATHARD_NAME,
                "Roll 4 swords dices to destroy the pirate boat and collecting his treasure or die trying and lose the equivalent", 
                Preferences.CARD_PIRATEBOATHARD_IMG,
                Preferences.CARD_PIRATEBOATHARD_QTY
        );
        cardQtyManager(
                Preferences.CARD_CHEST_NAME,
                "Protect the dices of your choice by putting it inside the chest", 
                Preferences.CARD_CHEST_IMG,
                Preferences.CARD_CHEST_QTY
        );
        cardQtyManager(
                Preferences.CARD_PIECEOFGOLD_NAME,
                "Start your turn with this additional Piece of gold", 
                Preferences.CARD_PIECEOFGOLD_IMG,
                Preferences.CARD_PIECEOFGOLD_QTY
        );
        cardQtyManager(Preferences.CARD_DIAMOND_NAME,
                "Start your turn with this additional Brilliant Diamond",
                Preferences.CARD_DIAMOND_IMG,
                Preferences.CARD_DIAMOND_QTY
        );

        Collections.shuffle(cards);
        logger.info("Cards has been created and shuffled !");
    }

    private void cardQtyManager(String name, String description, String imagePath, int qty) {
        for (int i = 0; i < qty; i++) {
            cards.add(new Card(name, description, new Image(imagePath)));
        }
    }

    public List<Dice> getDices() {
        return dices;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Turn getTurn() {
        return turn;
    }
}
