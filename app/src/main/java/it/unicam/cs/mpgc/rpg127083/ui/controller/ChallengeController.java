package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.Choice;
import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameState;
import it.unicam.cs.mpgc.rpg127083.core.dto.ChoiceOutcome;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.core.mechanics.Challenge;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
    @FXML
    private Label actLabel;
    @FXML
    private Label waitLabel;
    @FXML
    private Label lifeBarLabel;
    @FXML
    private Label energyBarLabel;
    @FXML
    private Label staminaBarLabel;
    @FXML
    private Label cubsLabel;
    @FXML
    private Label cubsCounterLabel;


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
        Challenge current = gameEngine.getCurrentChallenge();
        if(current != null){
            Choice choice = current.getWaitChoice();
            animateStatsUpdate(choice.getLifeEffect(), choice.getEnergyEffect(),
                    choice.getStaminaEffect(), choice.getCubsEffect());
        }
        showChoiceOutcome(gameEngine.executeWaitChoice());
    }

    private void handleActChoice() {
        Challenge current = gameEngine.getCurrentChallenge();
        if(current != null){
            Choice choice = current.getActChoice();
            animateStatsUpdate(choice.getLifeEffect(), choice.getEnergyEffect(),
                    choice.getStaminaEffect(), choice.getCubsEffect());
        }
        showChoiceOutcome(gameEngine.executeActChoice());;
    }
    private void animateStatsUpdate(double lifeEffect, double energyEffect, double staminaEffect, int cubsEffect) {
        updateSingleLabel(lifeBarLabel, lifeEffect);
        updateSingleLabel(energyBarLabel, energyEffect);
        updateSingleLabel(staminaBarLabel, staminaEffect);
        updateSingleLabel(cubsCounterLabel, cubsEffect);
    }
    private void updateSingleLabel(Label label, double effect){
        if(label == null) return;
        formatTextLabelEffect(label, effect);
        handleFadeTransition(label);
    }
    private void formatTextLabelEffect(Label label, double effect){
        String text = (effect >= 0 ? "+" : "") + (int) effect;
        label.setText(text);
        if (effect >= 0)
            label.setTextFill(Color.web("#87CEEB"));
        else
            label.setTextFill(Color.web("#e74c3c"));
    }

    private void handleFadeTransition(Label label) {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.30));
        FadeTransition fade = new FadeTransition(Duration.seconds(0.40), label);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        SequentialTransition sequence = new SequentialTransition(pause, fade);
        sequence.setOnFinished(e -> label.setText(""));
        sequence.play();
    }

    private void updateChallengeUI(){
        Challenge current = gameEngine.getCurrentChallenge();
        if(current != null) {
            challengeDescriptionLabel.setText(current.getDescription());
            actLabel.setText(current.getActChoice().getDescription());
            waitLabel.setText(current.getWaitChoice().getDescription());
        }
        else
            showWin(null);
        updateStats();
        resetScreen();
    }

    private void updateStats() {
        Animal animal = gameEngine.getPlayer();
        lifeBar.setProgress(animal.getLife() / 100.0);
        energyBar.setProgress(animal.getEnergy() / 100.0);
        staminaBar.setProgress(animal.getStamina() / 100.0);
        cubsLabel.setText("Prole: "+ animal.getCubs());
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
            showGameOver(outcome.description());
            return;
        }
        if(gameEngine.checkGameState()== GameState.VICTORY){
            showWin(outcome.description());
            return;
        }
        outcomeLabel.setText(outcome.description());
        showButtons();
    }

    private void showButtons(){
        outcomeLabel.setVisible(true);
        outcomeLabel.setManaged(true);
        nestButton.setVisible(true);
        nestButton.setManaged(true);
        nextChallengeButton.setVisible(true);
        nextChallengeButton.setManaged(true);
        actButton.setDisable(true);
        waitButton.setDisable(true);
    }

    private void showGameOver(String s){
        challengeDescriptionLabel.setText("SEI MORTO");
        outcomeLabel.setText(s + "\nLa natura ha fatto il suo corso.");
        freezeButtons();
    }

    private void freezeButtons() {
        outcomeLabel.setVisible(true);
        outcomeLabel.setManaged(true);
        actButton.setDisable(true);
        waitButton.setDisable(true);
        nestButton.setVisible(false);
        nextChallengeButton.setVisible(false);
        backToMenu.setVisible(true);
    }

    private void showWin(String s){
        challengeDescriptionLabel.setText("SEI SOPRAVVISSUTO");
        if(s.equals(null))
            outcomeLabel.setText("La natura non ti ha sopraffatto");
        else
            outcomeLabel.setText(s + "\nLa natura non ti ha sopraffatto");
        freezeButtons();
    }
}
