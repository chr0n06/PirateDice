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
    Map<Integer, String> sides = new HashMap<>();
    private String actualPosition;

    Random rnd = new Random();

    public Dice() {
        this.id = idIndex;
        initializeFaces();
        this.actualPosition = rollDice();
    }

    private void initializeFaces() {
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

    public String rollDice() {
        try {
            return this.sides.get(rnd.nextInt(Preferences.DICE_SIDE_QTY));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getActualPosition() {
        return actualPosition;
    }

}
