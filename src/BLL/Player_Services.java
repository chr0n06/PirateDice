/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Repository;
import Model.Player;
import java.util.List;

/**
 *
 * @author chr0n06
 */
public class Player_Services {
    public Repository repo;
    private static Player_Services player_services = null;

    private Player_Services() {
        repo = Repository.getInstance();
    }

    public static Player_Services getInstance() {
        if (player_services == null) {
            player_services = new Player_Services();
        }
        return player_services;
    }

    public List<Player> getAllPlayers() {
        return repo.getPlayers();
    }
}
