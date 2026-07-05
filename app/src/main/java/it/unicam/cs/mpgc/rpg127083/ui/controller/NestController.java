package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NestController {
    private final GameEngine gameEngine;
    private final SceneManager sceneManager;

    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button challengeButton;
    @FXML
    private ProgressBar lifeBar;
    @FXML
    private ProgressBar energyBar;
    @FXML
    private ProgressBar staminaBar;
    @FXML
    private Label animalTypeLabel;
    @FXML
    private Label habitatLabel;
    @FXML
    private Label lastChallenge;
    @FXML
    private Button escButton;

    public NestController(GameEngine gameEngine, SceneManager sceneManager) {
        this.gameEngine = gameEngine;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize(){
        saveButton.setOnAction(event -> handleSave());
        loadButton.setOnAction(event -> handleLoad());
        challengeButton.setOnAction(event -> {
            ChallengeController challengeController = new ChallengeController(gameEngine, sceneManager);
            sceneManager.switchScene("/view/ChallengeView.fxml", challengeController);
        });
        showStats();
        escButton.setOnAction(event -> handleExit());
    }
    private void handleSave(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Salva Partita");
        dialog.setHeaderText("Inserisci nome slot:");
        dialog.showAndWait().ifPresent(gameEngine::saveGame);
    }

    private void handleLoad() {
        SavesController savesController = new SavesController(sceneManager, gameEngine, true);
        sceneManager.switchScene("/view/SavesView.fxml", savesController);
    }

    private void handleExit(){
        StartMenuController startMenu = new StartMenuController(gameEngine, sceneManager);
        sceneManager.switchScene("/view/StartMenuView.fxml", startMenu);
    }

    private void showStats(){
        Animal player = gameEngine.getPlayer();
        if (player == null) return;
        lifeBar.setProgress(player.getLife() / 100.0);
        energyBar.setProgress(player.getEnergy() / 100.0);
        staminaBar.setProgress(player.getStamina() / 100.0);
        animalTypeLabel.setText("Animale: " + player.getType());
        habitatLabel.setText("Habitat: " + player.getHabitat().getLabel());
        lastChallenge.setText("Stage: " + gameEngine.getCurrentStage());
    }
}
