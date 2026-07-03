package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.GameState;
import it.unicam.cs.mpgc.rpg127083.core.dto.ChoiceOutcome;
import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ChallengeController {
    private final GameEngine gameEngine;
    private final SceneManager sceneManager;


    @FXML
    private Button actButton;
    @FXML
    private Button waitButton;
    @FXML
    private Label challengeDescriptionLabel;
    @FXML
    private Label outcomeLabel;
    @FXML
    private Button nestButton;
    @FXML
    private Button nextChallengeButton;
    @FXML
    private ProgressBar lifeBar;
    @FXML
    private ProgressBar energyBar;
    @FXML
    private ProgressBar staminaBar;
    @FXML
    private Button backToMenu;


    public ChallengeController(GameEngine gameEngine, SceneManager sceneManager) {
        this.gameEngine = gameEngine;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize(){
        updateChallengeUI();
        actButton.setOnAction(event -> handleActChoice());
        waitButton.setOnAction(event -> handleWaitChoice());
        nestButton.setOnAction(event -> backToNest());
        nextChallengeButton.setOnAction(event -> nextChallenge());
        backToMenu.setOnAction(event -> handleEnding());
    }

    private void handleEnding() {
        StartMenuController startMenu =
                new StartMenuController(gameEngine, sceneManager);
        sceneManager.switchScene("/view/StartMenuView.fxml", startMenu);
    }

    private void nextChallenge() {
        updateChallengeUI();
    }

    private void backToNest() {
        NestController nestController = new NestController(gameEngine, sceneManager);
        sceneManager.switchScene("/view/NestView.fxml", nestController);
    }

    private void handleWaitChoice() {
        showChoiceOutcome(gameEngine.executeWaitChoice());
    }

    private void handleActChoice() {
        showChoiceOutcome(gameEngine.executeActChoice());;
    }

    private void updateChallengeUI(){
        Challenge current = gameEngine.getCurrentChallenge();
        if(current != null)
            challengeDescriptionLabel.setText(current.getDescription());
        else
            showWin();
        updateStats();
        resetScreen();
    }


    private void updateStats() {
        Animal animal = gameEngine.getPlayer();
        lifeBar.setProgress(animal.getLife() / 100.0);
        energyBar.setProgress(animal.getEnergy() / 100.0);
        staminaBar.setProgress(animal.getStamina() / 100.0);
    }
    private void resetScreen() {
        outcomeLabel.setVisible(false);
        outcomeLabel.setManaged(false);
        nestButton.setVisible(false);
        nestButton.setManaged(false);
        nextChallengeButton.setVisible(false);
        nextChallengeButton.setManaged(false);
        actButton.setDisable(false);
        waitButton.setDisable(false);
        backToMenu.setVisible(false);
    }

    private void showChoiceOutcome(ChoiceOutcome outcome){
        updateStats();
        if(gameEngine.checkGameState()== GameState.GAME_OVER){
            showGameOver();
            return;
        }
        if(gameEngine.checkGameState()== GameState.VICTORY){
            showWin();
            return;
        }
        outcomeLabel.setText(outcome.description());
        outcomeLabel.setVisible(true);
        outcomeLabel.setManaged(true);
        nestButton.setVisible(true);
        nestButton.setManaged(true);
        nextChallengeButton.setVisible(true);
        nextChallengeButton.setManaged(true);
        actButton.setDisable(true);
        waitButton.setDisable(true);
    }

    private void showGameOver() {
        challengeDescriptionLabel.setText("SEI MORTO");
        outcomeLabel.setText("La natura ha fatto il suo corso.");
        outcomeLabel.setVisible(true);
        outcomeLabel.setManaged(true);
        actButton.setDisable(true);
        waitButton.setDisable(true);
        nestButton.setVisible(false);
        nextChallengeButton.setVisible(false);
        backToMenu.setVisible(true);
    }

    private void showWin(){
        challengeDescriptionLabel.setText("SEI SOPRAVVISSUTO");
        outcomeLabel.setText("La natura non ti ha sopraffatto");
        outcomeLabel.setVisible(true);
        outcomeLabel.setManaged(true);
        actButton.setDisable(true);
        waitButton.setDisable(true);
        nestButton.setVisible(false);
        nextChallengeButton.setVisible(false);
        backToMenu.setVisible(true);
    }

}
