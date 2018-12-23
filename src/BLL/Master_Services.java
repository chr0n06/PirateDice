/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Repository;

/**
 *
 * @author Maxime
 */
public class Master_Services {

    public Player_Service player_service = null;
    public Card_Service card_service = null;
    public Dice_Service dice_service = null;
    public Turn_Service turn_services = null;

    public Repository repo;
    private static Master_Services master_services = null;

    private Master_Services() {
        repo = Repository.getInstance();
        player_service = Player_Service.getInstance();
        card_service = Card_Service.getInstance();
        dice_service = Dice_Service.getInstance();
        turn_services = Turn_Service.getInstance();
    }

    public static Master_Services getInstance() {
        if (master_services == null) {
            master_services = new Master_Services();
        }
        return master_services;
    }

}
