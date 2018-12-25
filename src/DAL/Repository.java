package DAL;

import Model.Card;
import Model.Dice;
import Model.Player;
import Settings.Preferences;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    private static Repository repository = null;

    private List<Dice> dices;
    private List<Player> players;
    private List<Card> cards;

    // private constructor restricted to this class itself 
    private Repository() {

        this.dices = new ArrayList<>();
        this.players = new ArrayList<>();
        this.cards = new ArrayList<>();
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
        cardQtyManager("WitchCard", "You have the capability to reroll one Death dice by the end of your turn!",
                Preferences.CARD_WITCH_QTY);
        cardQtyManager("SimpleSkullCard", "You already start your turn with only 2 lifes", 
                Preferences.CARD_SIMPLESKULL_QTY);
        cardQtyManager("DoubleSkullCard", "You already start your turn with only 1 life", 
                Preferences.CARD_DOUBLESKULL_QTY);
        cardQtyManager("PirateCard", "Your points will be doubled at the end of that turn", 
                Preferences.CARD_PIRATE_QTY);
        cardQtyManager("MonkeyParrotCard", "The monkey and Parrot are considered similars dices", 
                Preferences.CARD_MONKEY_PIRATE_QTY);
        cardQtyManager("EasyPirateBoatCard", "Roll 2 swords dices to destroy the pirate boat and collecting his treasure or die trying and lose the equivalent", 
                Preferences.CARD_PIRATEBOATEASY_QTY);
        cardQtyManager("MediumPirateBoatCard", "Roll 3 swords dices to destroy the pirate boat and collecting his treasure or die trying and lose the equivalent", 
                Preferences.CARD_PIRATEBOATMEDIUM_QTY);
        cardQtyManager("HardPirateBoatCard", "Roll 4 swords dices to destroy the pirate boat and collecting his treasure or die trying and lose the equivalent", 
                Preferences.CARD_PIRATEBOATHARD_QTY);
        cardQtyManager("ChestCard", "Roll till your death but don't forget the protect the dices of your choice by putting it inside the chest", 
                Preferences.CARD_CHEST_QTY);
        cardQtyManager("PieceOfGoldCard", "Start your turn with this additional Piece of gold", 
                Preferences.CARD_PIECEOFGOLD_QTY);
        cardQtyManager("BrilliantDiamondCard", "Start your turn with this additional Brilliant Diamond", 
                Preferences.CARD_BRILLIANT_DIAMOND_QTY);

        Collections.shuffle(cards);
    }

    public void cardQtyManager(String name, String description, int qty) {
        for (int i = 0; i < qty; i++) {
            cards.add(new Card(name, description));
        }
    }

    public List<Dice> getDices() {
        return dices;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getCards() {
        return cards;
    }

}
