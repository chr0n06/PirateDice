package Controllers;

import BLL.Services;
import Settings.Preferences;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BoardGame_Controller implements Initializable {

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

    List<ImageView> dices ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dices = Arrays.asList(dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8);
    }

    @FXML
    void onactionNextTurn(ActionEvent event) {

    }

    @FXML
    void onActionPlay(ActionEvent event) {
        Services.rollAllDices();
        int index = 0;
        for (ImageView dice : dices) {
            dice.setImage(new Image("Assets/DicesLayouts/" + Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(index++).getState() + ".png"));
            
        }
    }//onActionPlay

}//BoardGame
