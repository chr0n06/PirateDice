package Controllers;

import BLL.Services;
import Settings.Preferences;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class BoardGame_Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label name;

    @FXML
    private Label points;

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
    private ImageView cardView;

    @FXML
    private Label cardName;

    @FXML
    private Text cardDescription;

    List<ImageView> dices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dices = Arrays.asList(dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8);
    }//initialize

    @FXML
    void onactionNextTurn(ActionEvent event) {
        Services.nextTurn();

        cardName.setText(Services.getTurn().getCard().getName());
        cardDescription.setText(Services.getTurn().getCard().getDescription());
        name.setText(Services.getTurn().getPlayer().getName());
        cardView.setImage(Services.getTurn().getCard().getImage());
        points.setText(String.valueOf(Services.getTurn().getPlayer().getPoint()));
    }//onactionNextTurn

    @FXML
    void onActionPlay(ActionEvent event) {
        Services.rollAllDices();
        int index = 0;
        for (ImageView dice : dices) {
            dice.setImage(new Image("Assets/DicesLayouts/" + Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(index++).getState() + ".png"));

        }//for
    }//onActionPlay

    @FXML
    void MenuQuit(ActionEvent event) {
        Platform.exit();
    }//onActionPlay

}//BoardGame
