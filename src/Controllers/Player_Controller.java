/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAL.Repository;
import Model.Player;
import java.util.List;

/**
 *
 * @author chr0n06
 */
public class Player_Controller {
    public Repository repo;
    private static Player_Controller player_services = null;

    private Player_Controller() {
        repo = Repository.getInstance();
    }

    public static Player_Controller getInstance() {
        if (player_services == null) {
            player_services = new Player_Controller();
        }
        return player_services;
    }

    public List<Player> getAllPlayers() {
        return repo.getPlayers();
    }
}
