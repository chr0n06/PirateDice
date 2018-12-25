/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.Dice_Service;
import DAL.Repository;
import Model.Turn;
import Settings.Preferences;

/**
 *
 * @author chr0n06
 */
public class Turn_Service {

    public Repository repo;
    private static Turn_Service turn_services = null;
    private static Dice_Service dice_services = null;
    
    
    private Turn_Service() {
        repo = Repository.getInstance();

    }

    public static Turn_Service getInstance() {
        if (turn_services == null) {
            turn_services = new Turn_Service();
        }
        return turn_services;
    }

    public Turn newTurn() {
        Turn turn = new Turn(repo.getPlayers().get(Preferences.PLAYER_TURN_INDEX));
        Preferences.PLAYER_TURN_INDEX++;
        return turn;
    }

}
