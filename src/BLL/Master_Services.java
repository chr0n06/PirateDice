/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import Controllers.Player_Controller;
import Controllers.Card_Controller;
import Controllers.Dice_Controller;
import Controllers.Turn_Controller;
import DAL.Repository;

/**
 *
 * @author Maxime
 */
public class Master_Services {
    Player_Controller player_service = Player_Controller.getInstance();
    Card_Controller card_service = Card_Controller.getInstance();
    Dice_Controller dice_service = Dice_Controller.getInstance();
    Turn_Controller turn_services = Turn_Controller.getInstance();
    
    
    public Repository repo;
    private static Master_Services master_services = null;

    private Master_Services() {
        repo = Repository.getInstance();
    }

    public static Master_Services getInstance() {
        if (master_services == null) {
            master_services = new Master_Services();
        }
        return master_services;
    }

}
