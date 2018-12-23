package Controllers;

import Model.Dice;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardGame implements Initializable {

    Dice_Controller dc = Dice_Controller.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView dice1;

    @FXML
    private ImageView dice2;

    @FXML
    private ImageView dice3;

    @FXML
    private ImageView dice4;

    @FXML
    private ImageView dice5;

    @FXML
    private ImageView dice6;

    @FXML
    private ImageView dice7;

    @FXML
    private ImageView dice8;

    @FXML
    private ImageView dice9;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onActionPlay(ActionEvent event) {
        dc.rollAllDices();

        dice1.setImage(new Image("Assets/" + dc.getAllDices().get(0).getState() + ".jpg"));
        dice2.setImage(new Image("Assets/" + dc.getAllDices().get(1).getState() + ".jpg"));
        dice3.setImage(new Image("Assets/" + dc.getAllDices().get(2).getState() + ".jpg"));
        dice4.setImage(new Image("Assets/" + dc.getAllDices().get(3).getState() + ".jpg"));
        dice5.setImage(new Image("Assets/" + dc.getAllDices().get(4).getState() + ".jpg"));
        dice6.setImage(new Image("Assets/" + dc.getAllDices().get(5).getState() + ".jpg"));
        dice7.setImage(new Image("Assets/" + dc.getAllDices().get(6).getState() + ".jpg"));
        dice8.setImage(new Image("Assets/" + dc.getAllDices().get(7).getState() + ".jpg"));
      
    }//onActionPlay

}//BoardGame
