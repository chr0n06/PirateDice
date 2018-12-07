/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratedice;

import BLL.Services;
import Model.Card;
import Model.Dice;
import Model.Turn;
import java.util.List;

/**
 *
 * @author Maxime
 */
public class PirateDice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Services serv = Services.getInstance();

        List<Dice> dicesGame = serv.startGame();

        /*Testing Dices and states*/
 /*
        for (Dice dice : dicesGame) {
            System.out.println(dice.getId() + "--" + dice.getState() + "--" + dice.getIsDeath());

        }
        System.out.println("");
        serv.rollDices();

        for (Dice dice : dicesGame) {
            System.out.println(dice.getId() + "--" + dice.getState() + "--" + dice.getIsDeath());

        }
         */
 /*Testing Cards*/
 /*
        for (Card card : serv.getAllCards()) {
            System.out.println(card.getId() + "--" +card.getName());
        }
         */
 /*Testing turn*/
        Turn turn1 = new Turn(serv.getAllPlayers().get(0));
        
        serv.pickACard(turn1);
        
        System.out.println(turn1.getPlayer().getName() +" has picked the "+ turn1.getCard().getName() + " card!!!" );
        
        
        
    }

}
