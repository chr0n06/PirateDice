/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piratedice;

import Controllers.Card_Controller;
import Controllers.Dice_Controller;
import BLL.Master_Services;
import Controllers.Player_Controller;
import Controllers.Turn_Controller;
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
        Master_Services ms = Master_Services.getInstance();
        
        Player_Controller player_service = Player_Controller.getInstance();
        Card_Controller card_service = Card_Controller.getInstance();
        Dice_Controller dice_service = Dice_Controller.getInstance();
        Turn_Controller turn_services = Turn_Controller.getInstance();

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
        //Turn turn1 = new Turn(player_service.getAllPlayers().get(0));

       Turn turn1 = turn_services.newTurn();
        
        card_service.pickACard(turn1);

        System.out.println(turn1.getPlayer().getName() + " has picked the " + turn1.getCard().getName() + " card!!!");

        Scanner sc = new Scanner(System.in);
        System.out.println("1-Roll all dices");
        System.out.println("2-Roll specific dices");

        while (sc.hasNextInt()) {
            System.out.println("1-Roll all dices");
            System.out.println("2-Roll specific dices");
            switch (sc.nextInt()) {
                case 1:
                    dice_service.rollAllDices();
                    for (Dice dice : dice_service.getAllDices()) {
                        System.out.println(dice.getId() + "--" + dice.getState() + "--" + dice.isDeath());

                    }
                    break;
                case 2:
                    List<Integer> dicesToRoll = Arrays.asList(2, 4, 6);
                    dice_service.rollSpecificDices(dicesToRoll);
                    for (Dice dice : dice_service.getAllDices()) {
                        System.out.println(dice.getId() + "--" + dice.getState() + "--" + dice.isDeath());
                    }
                    break;

            }
        }
        sc.close();

    }

}
