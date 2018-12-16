/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAL.Repository;
import Model.Turn;
import Settings.Preferences;

/**
 *
 * @author chr0n06
 */
public class Turn_Controller {

    public Repository repo;
    private static Turn_Controller turn_services = null;
    private static Dice_Controller dice_services = null;
    Turn turn = null; 
    
    private Turn_Controller() {
        repo = Repository.getInstance();
        dice_services = Dice_Controller.getInstance();
        newTurn();
    }

    public static Turn_Controller getInstance() {
        if (turn_services == null) {
            turn_services = new Turn_Controller();
        }
        return turn_services;
    }

    public Turn newTurn() {
        Turn turn = new Turn(repo.getPlayers().get(Preferences.PLAYER_TURN_INDEX));
        Preferences.PLAYER_TURN_INDEX++;
        return turn;
    }
    
    public void shakeDice(){
        dice_services.rollAllDices();
        
    
     
    }

}
