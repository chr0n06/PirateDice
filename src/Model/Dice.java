/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Settings.Preferences;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Maxime
 */
public final class Dice {

    private static int idIndex = 1;

    private int id;
    private String state; //Face up of the dice 
    private boolean isDeath;

    Map<Integer, String> sides = new HashMap<>(); //Map of all faces
    Random rnd = new Random();

    //Constructor(s)
    public Dice() {
        this.id = idIndex;
        initializeDiceFaces();
        rollDice();
        this.isDeath = this.state == "Death";
        idIndex++;
    }

    //Method(s)
    private void initializeDiceFaces() {
        try {
            this.sides.put(0, "Parrot");
            this.sides.put(1, "Monkey");
            this.sides.put(2, "Gold");
            this.sides.put(3, "Diamond");
            this.sides.put(4, "Swords");
            this.sides.put(5, "Death");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void rollDice() {
        try {
            this.state = this.sides.get(rnd.nextInt(Preferences.DICE_SIDE_QTY));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public boolean getIsDeath() {
        return isDeath;
    }

 }
