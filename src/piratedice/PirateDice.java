/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratedice;

import Model.Dice;

/**
 *
 * @author Maxime
 */
public class PirateDice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        
        System.out.println(d1.getActualPosition());
        System.out.println(d2.getActualPosition());
        System.out.println(d2.rollDice());
        
    }
    
}
