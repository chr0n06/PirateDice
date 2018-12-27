/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Maxime
 */
public class Card {
    
    private static int idIndex = 1;
    
    private int id;
    private String name;
    private String Description;
    private Image image;

    public Card(String name, String Description, Image image) {
        this.id = idIndex;
        this.name = name;
        this.Description = Description;
        this.image = image;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
   
}
