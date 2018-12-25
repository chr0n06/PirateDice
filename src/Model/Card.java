/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Random;

/**
 *
 * @author Maxime
 */
public class Card {
    
    private static int idIndex = 1;
    
    private int id;
    private String name;
    private String Description;

    public Card(String name, String Description) {
        this.id = idIndex;
        this.name = name;
        this.Description = Description;
        idIndex++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }   

    public String getDescription() {
        return Description;
    }    
}
