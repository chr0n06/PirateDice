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

    Map<Integer, String> sides = new HashMap<>(); //Map of all faces
    Random rnd = new Random();

    //Constructor(s)
    public Dice() {
        this.id = idIndex++;
        initializeDiceFaces();
        rollDice();
    }

    //Method(s)
    private void initializeDiceFaces() {
        try {
            this.sides.put(0, Preferences.DICE_PARROT_NAME);
            this.sides.put(1, Preferences.DICE_MONKEY_NAME);
            this.sides.put(2, Preferences.DICE_GOLD_NAME);
            this.sides.put(3, Preferences.DICE_DIAMOND_NAME);
            this.sides.put(4, Preferences.DICE_SWORDS_NAME);
            this.sides.put(5, Preferences.DICE_DEATH_NAME);
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
    
    public boolean isDeath() {
        if (this.state == Preferences.DICE_DEATH_NAME) {
            return true;
        } else {
            return false;
        }
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }    
   
     public void setState(String state) {
        this.state = state;
    }   

}
