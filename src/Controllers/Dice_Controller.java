/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAL.Repository;
import Model.Dice;
import java.util.List;

/**
 *
 * @author chr0n06
 */
public class Dice_Controller {

    public Repository repo;
    private static Dice_Controller dice_services = null;

    private Dice_Controller() {
        repo = Repository.getInstance();
    }

    public static Dice_Controller getInstance() {
        if (dice_services == null) {
            dice_services = new Dice_Controller();
        }
        return dice_services;
    }

    public List<Dice> getAllDices() {
        return repo.getDices();
    }

    public void rollAllDices() {
        for (Dice dice : repo.getDices()) {
            dice.rollDice();
        }
    }

    public void rollSpecificDices(List<Integer> dices) {
        for (Integer diceId : dices) {
            if(isDiceRollable(diceId)){
               repo.getDices().get(diceId - 1).rollDice(); 
            }
            
        }
    }
   
    public boolean isDiceRollable(int diceId) {
        return !repo.getDices().get(diceId - 1).isDeath();
    }
    

}
