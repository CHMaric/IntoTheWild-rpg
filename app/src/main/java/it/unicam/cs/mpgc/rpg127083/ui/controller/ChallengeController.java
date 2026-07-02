package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.GameEngine;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

public class ChallengeController {
    private final GameEngine gameEngine;
    private final SceneManager sceneManager;


    @FXML
    private Button actButton;
    @FXML
    private Button waitButton;
    @FXML
    private Label challengeDesccriptionLabel;

    public ChallengeController(GameEngine gameEngine, SceneManager sceneManager) {
        this.gameEngine = gameEngine;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize(){
        updateChallengeUI();
        actButton.setOnAction(event ->{
            String outcome = gameEngine.executeActChoice();
            showChoiceOutcome(outcome);
        });

        waitButton.setOnAction(event -> {
            String outcome = gameEngine.executeWaitChoice();
            showChoiceOutcome(outcome);
        });
    }

    private void updateChallengeUI(){
        Challenge current = gameEngine.getCurrentChallenge();
        if(current != null){
            challengeDesccriptionLabel.setText(current.getDescription());
        } else {
            showWin();
        }
    }

    private void showChoiceOutcome(String s){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Esito della decisione");
        alert.setHeaderText(null);
        alert.setContentText(s + "Scegli la tua prossima mossa");

        ButtonType nestButton = new ButtonType("Torna al nido");
        ButtonType nextButton = new ButtonType("Prossima sfida");
        alert.getButtonTypes().setAll(nestButton, nextButton);

        Optional<ButtonType> choice = alert.showAndWait();
        if(choice.isPresent() && choice.get() == nestButton){
            NestController nestController = new NestController(gameEngine, sceneManager);
            sceneManager.switchScene("/view/NestView.fxml", nestController);
        }
        else
            updateChallengeUI();
    }

    private void showWin(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hai vinto!");
        alert.setContentText("Sei sopravvissuto alle difficoltà della vita nella natura.");
        alert.showAndWait();
        StartMenuController startMenu = new StartMenuController(gameEngine, sceneManager);
        sceneManager.switchScene("/view/StartMenuView.fxml", startMenu);
    }


}
