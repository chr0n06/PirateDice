/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratedice;

import BLL.Card_Services;
import BLL.Dice_Services;
import BLL.Player_Services;
import Model.Dice;
import Model.Turn;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Maxime
 */
public class PirateDice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Player_Services player_service = Player_Services.getInstance();
        Card_Services card_service = Card_Services.getInstance();
        Dice_Services dice_service = Dice_Services.getInstance();

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
        Turn turn1 = new Turn(player_service.getAllPlayers().get(0));

        card_service.pickACard(turn1);

        System.out.println(turn1.getPlayer().getName() + " has picked the " + turn1.getCard().getName() + " card!!!");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            switch (sc.nextInt()) {
                case 1:
                    /*serv.rollAllDices();
                    for (Dice dice : serv.getDices()) {
                        System.out.println(dice.getId() + "--" + dice.getState() + "--" + dice.getIsDeath());
                    }*/
                    break;
                case 2:
                    List<Integer> dicesToRoll = Arrays.asList(2,4,6);
                    dice_service.rollSpecificDices(dicesToRoll);
                    for (Dice dice : dice_service.getAllDices()) {
                        System.out.println(dice.getId() + "--" + dice.getState() + "--" + dice.isDeath());
                    }
                    break;
                case 3:
                    dice_service.rollAllDices();
                    for (Dice dice : dice_service.getAllDices()) {
                        System.out.println(dice.getId() + "--" + dice.getState() + "--" + dice.isDeath());
                        
                    }
                    break;

            }
        }
        sc.close();

    }

}