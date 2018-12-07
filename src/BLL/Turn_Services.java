/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Repository;
import Model.Turn;
import Settings.Preferences;

/**
 *
 * @author chr0n06
 */
public class Turn_Services {

    public Repository repo;
    private static Turn_Services turn_services = null;
    private static Dice_Services dice_services = null;
    Turn turn = null; 
    
    private Turn_Services() {
        repo = Repository.getInstance();
        dice_services = Dice_Services.getInstance();
        newTurn();
    }

    public static Turn_Services getInstance() {
        if (turn_services == null) {
            turn_services = new Turn_Services();
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
