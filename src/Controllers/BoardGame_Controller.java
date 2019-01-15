package Controllers;

import BLL.Services;
import Settings.Preferences;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class BoardGame_Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button acceptPts;

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
    private CheckBox checkBox1;

    @FXML
    private CheckBox checkBox2;

    @FXML
    private CheckBox checkBox3;

    @FXML
    private CheckBox checkBox4;

    @FXML
    private CheckBox checkBox5;

    @FXML
    private CheckBox checkBox6;

    @FXML
    private CheckBox checkBox7;

    @FXML
    private CheckBox checkBox8;

    @FXML
    private ImageView cardView;

    @FXML
    private Label cardName;

    @FXML
    private Text cardDescription;

    @FXML
    private Label pointsTemp;

    @FXML
    private Button roll_btn;

    @FXML
    private Button witchCardPower;

    List<ImageView> dices;
    List<CheckBox> checkboxes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dices = Arrays.asList(dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8);
        checkboxes = Arrays.asList(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8);

        Services.nextTurn();
        cardName.setText(Services.getTurn().getCard().getName());
        cardDescription.setText(Services.getTurn().getCard().getDescription());
        name.setText(Services.getTurn().getPlayer().getName());
        cardView.setImage(Services.getTurn().getCard().getImage());
        points.setText(String.valueOf(Services.getTurn().getPlayer().getPoint()));

        //WitchCard Influence
        if (Services.getTurn().getCard().getName().equals("WitchCard")) {
            this.witchCardPower.setVisible(true);
        } else {
            this.witchCardPower.setVisible(false);
        }

    }//initialize

    @FXML
    void MenuQuit(ActionEvent event) {
        Platform.exit();
    }//onActionPlay

    @FXML
    void onactionNextTurn(ActionEvent event) {
        this.acceptPts.setVisible(true);
        this.roll_btn.setVisible(true);

        Services.nextTurn();

        //WitchCard Influence
        if (Services.getTurn().getCard().getName().equals("WitchCard")) {
            this.witchCardPower.setVisible(true);
        } else {
            this.witchCardPower.setVisible(false);
        }
        
        cardName.setText(Services.getTurn().getCard().getName());
        cardDescription.setText(Services.getTurn().getCard().getDescription());
        name.setText(Services.getTurn().getPlayer().getName());
        cardView.setImage(Services.getTurn().getCard().getImage());
        points.setText(String.valueOf(Services.getTurn().getPlayer().getPoint()));

        Services.rollAllDices();
        resetLayout();

    }//onactionNextTurn

    @FXML
    void OnActionRoll(ActionEvent event) {
        Services.rollSpecificDices(checkWichCheckBoxIsSelected());
        resetLayout();
        fillImageInDice();

        updatePointsTemp();//Visual aspect
    }

    @FXML
    void onactionwitchCardPower(ActionEvent event){
        this.witchCardPower.setVisible(false);
        
        //implement action here!
    }
    
    @FXML
    void OnActionAcceptPoints(ActionEvent event) {
        Services.acceptPoints();
        this.points.setText(String.valueOf(Services.getTurn().getPlayer().getPoint()));
        this.pointsTemp.setText("0");

        this.roll_btn.setVisible(false);
        this.acceptPts.setVisible(false);

    }

    private void updatePointsTemp() {
        int tempPoints = Services.getTempPoints();
        if (tempPoints > 0) {
            pointsTemp.setText(String.valueOf("+" + tempPoints));
        } else if (tempPoints < 0) {
            pointsTemp.setText(String.valueOf("-" + tempPoints));
        }
    }

    private List<Integer> checkWichCheckBoxIsSelected() {
        List<Integer> boxchecked = new ArrayList<>();
        int index = 1;
        for (CheckBox checkboxe : checkboxes) {
            if (checkboxe.isSelected()) {
                boxchecked.add(index);
            }//if
            index++;
        }//for
        if (boxchecked.size() == 1) {
            System.out.println("Select minimum two dice!"); //Will have to manage a popup on the main thread
            boxchecked.clear();
        }//if
        return boxchecked;
    }

    private void resetLayout() {
        resetDices();
        resetPointsTemp();
    }

    private void resetDices() {
        for (ImageView dice : dices) {
            dice.setImage(new Image("Assets/DicesLayouts/mystery.png"));
        }//for
        for (CheckBox checkbox : checkboxes) {
            /*Make the checkboxex more interesting for the player
            
            /*BackgroundImage img = new BackgroundImage(new Image("Assets/DicesLayouts/mystery.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background background = new Background(img);
            checkbox.setBackground(background);*/
            checkbox.setDisable(false);
            checkbox.setSelected(false);
        }//for
    }

    private void resetPointsTemp() {
        pointsTemp.setText("0");
    }

    private void fillImageInDice() {
        int index = 0;
        for (ImageView dice : dices) {
            if (Services.getAllDices().get(index).getState() == "Death") {
                checkboxes.get(index).setDisable(true);
                checkboxes.get(index).setSelected(false);
            } else {
                checkboxes.get(index).setDisable(false);
            }
            dice.setImage(new Image("Assets/DicesLayouts/" + Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(index++).getState() + ".png"));
        }//for
    }//fillImageInDice

}//BoardGame
