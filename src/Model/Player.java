/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Settings.Preferences;

/**
 *
 * @author Maxime
 */
public class Player {
    
    private int id;
    private String name;
    private int point;

    //Constructor(s)
    public Player(String name) {
        this.id = id;
        this.name = name;
        this.point = Preferences.PLAYER_STARTING_POINTS;
    }
    
    //Getters & Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    
    public int getPoint() {
        return point;
    }   
    
}
