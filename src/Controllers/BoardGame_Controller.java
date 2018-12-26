package Controllers;

import BLL.Services;
import Settings.Preferences;
import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
 
    @FXML
    void onactionNextTurn(ActionEvent event){
        
    }
    @FXML
    void onActionPlay(ActionEvent event) {
        
        Services.rollAllDices();

        dice1.setImage(new Image("Assets/DicesLayouts/"+ Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(0).getState() + ".png"));
        dice2.setImage(new Image("Assets/DicesLayouts/"+ Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(1).getState() + ".png"));
        dice3.setImage(new Image("Assets/DicesLayouts/"+ Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(2).getState() + ".png"));
        dice4.setImage(new Image("Assets/DicesLayouts/"+ Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(3).getState() + ".png"));
        dice5.setImage(new Image("Assets/DicesLayouts/"+ Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(4).getState() + ".png"));
        dice6.setImage(new Image("Assets/DicesLayouts/"+ Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(5).getState() + ".png"));
        dice7.setImage(new Image("Assets/DicesLayouts/"+ Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(6).getState() + ".png"));
        dice8.setImage(new Image("Assets/DicesLayouts/"+ Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(7).getState() + ".png"));
      
    }//onActionPlay

}//BoardGame
