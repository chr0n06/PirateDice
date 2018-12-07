package DAL;

import Model.Dice;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chr0n06
 */
public class Repository {

    private static List<Dice> dices;
    private static Repository repository = null;

    // private constructor restricted to this class itself 
    private Repository() {
        this.dices = new ArrayList<>();
        initializeBoard();
    }

    // static method to create instance of Singleton class 
    public static Repository getInstance() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }

    public void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            dices.add(new Dice());
        }
    }

    public static List<Dice> getDices() {
        return dices;
    }

    public static void setDices(List<Dice> aDices) {
        dices = aDices;
    }

}
