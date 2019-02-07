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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    private ToggleButton chestSave1;

    @FXML
    private ToggleButton chestSave2;
    @FXML

    private ToggleButton chestSave3;
    @FXML

    private ToggleButton chestSave4;

    @FXML
    private ToggleButton chestSave5;

    @FXML
    private ToggleButton chestSave6;

    @FXML
    private ToggleButton chestSave7;

    @FXML
    private ToggleButton chestSave8;

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

    @FXML
    private Button rollDicesToKill;

    @FXML
    private AnchorPane anchorPane_Background;

    List<ImageView> dices;
    List<CheckBox> checkboxes;
    List<ToggleButton> chestSaves;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dices = Arrays.asList(dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8);
        this.checkboxes = Arrays.asList(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8);
        this.chestSaves = Arrays.asList(chestSave1, chestSave2, chestSave3, chestSave4, chestSave5, chestSave6, chestSave7, chestSave8);
        this.acceptPts.setVisible(true);
        this.roll_btn.setVisible(true);
        this.rollDicesToKill.setVisible(false);
        Services.nextTurn();
        this.witchCardPower.setVisible(Services.witchCardInfluence());
        this.anchorPane_Background.setStyle("-fx-background-image: url('/Assets/Board/Island.png')");
        this.cardName.setText(Services.getTurn().getCard().getName());
        this.cardDescription.setText(Services.getTurn().getCard().getDescription());
        this.name.setText(Services.getTurn().getPlayer().getName());
        this.cardView.setImage(Services.getTurn().getCard().getImage());
        this.points.setText(String.valueOf(Services.getTurn().getPlayer().getPoint()));

        this.chestSavesDisplay();

        Services.rollAllDices();
        this.resetLayout();

    }//initialize

    @FXML
    void MenuQuit(ActionEvent event) {
        Platform.exit();
    }//onActionPlay

    @FXML
    void onActionRollDicesToKill(ActionEvent event) {
        this.roll_btn.setVisible(false);
        this.acceptPts.setVisible(false);

        Services.rollAllDicesFromDeathIsland();

        this.resetLayout();
        this.fillImageInDice();

        updatePointsTemp();//to change : minus points
    }

    @FXML
    void onactionNextTurn(ActionEvent event) {
        this.acceptPts.setVisible(true);
        this.roll_btn.setVisible(true);
        this.rollDicesToKill.setVisible(false);
        Services.nextTurn();
        this.witchCardPower.setVisible(Services.witchCardInfluence());
        this.anchorPane_Background.setStyle("-fx-background-image: url('/Assets/Board/Island.png')");
        this.cardName.setText(Services.getTurn().getCard().getName());
        this.cardDescription.setText(Services.getTurn().getCard().getDescription());
        this.name.setText(Services.getTurn().getPlayer().getName());
        this.cardView.setImage(Services.getTurn().getCard().getImage());
        this.points.setText(String.valueOf(Services.getTurn().getPlayer().getPoint()));

        this.chestSavesDisplay();
        Services.rollAllDices();
        this.resetLayout();

    }//onactionNextTurn

    @FXML
    void OnActionRoll(ActionEvent event) {

        Services.rollSpecificDices(checkWichCheckBoxIsSelected());
        this.resetLayout();
        this.fillImageInDice();

        //Background Manager
        if (Services.getTurn().isInitiated() == false) {
            if (Services.getTurn().getLifes() < 0) {
                this.anchorPane_Background.setStyle("-fx-background-image: url('/Assets/Board/DeadIsland.png')");
                this.acceptPts.setVisible(false);
                this.roll_btn.setVisible(false);
                this.rollDicesToKill.setVisible(true);
            } else {
                Services.getTurn().setInitiated(true);
                System.out.println("Turn initiated");
                this.anchorPane_Background.setStyle("-fx-background-image: url('/Assets/Board/Island.png')");
            }
        }
        this.updatePointsTemp();//Visual aspect

    }

    @FXML
    void onactionwitchCardPower(ActionEvent event) {
        int diceIndex = Services.findFirstDeathDice();

        if (Services.isOneDeathDice()) {//Check if there is one death Dice
            Services.getTurn().setLifes(Services.getTurn().getLifes() + 1); //Add a life
            System.out.println("One life has been added");

            //Set one Death Dice to be changeable                             
            this.checkboxes.get(diceIndex).setDisable(false); //Find the checkboxe that need to be activated
            Services.getAllDices().get(diceIndex).setState("mystery"); //Change state of the death dice
            this.dices.get(diceIndex).setImage(new Image("Assets/DicesLayouts/mystery.png")); //Reset dice image 

            this.witchCardPower.setVisible(false);
            System.out.println("At least one dice is death, proceed...");
        } else {
            System.out.println("No Dice is death for the moment!");
        }
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
        this.pointsTemp.setText("0");
    }

    private void fillImageInDice() {
        int index = 0;
        for (ImageView dice : dices) {
            if (Services.getAllDices().get(index).getState() == "Death") {
                checkboxes.get(index).setDisable(true);
                checkboxes.get(index).setSelected(false);
            } else {
                checkboxes.get(index).setDisable(false);
            }//if
            dice.setImage(new Image("Assets/DicesLayouts/" + Preferences.DICE_LAYOUT + "/" + Services.getAllDices().get(index++).getState() + ".png"));
        }//for
    }//fillImageInDice

    private void chestSavesDisplay() {
        if (Services.getTurn().getCard().getName().equals("ChestCard")) {
            for (ToggleButton chestSave : chestSaves) {
                chestSave.setSelected(false);
                chestSave.setVisible(true);
            }//for
        } else {
            for (ToggleButton chestSave : chestSaves) {
                chestSave.setVisible(false);
            }//for
        }//if
    }//chestSavesDisplay

}//BoardGame
