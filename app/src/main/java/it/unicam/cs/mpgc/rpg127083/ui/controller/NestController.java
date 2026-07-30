package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.ui.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NestController {
    private final GameEngine gameEngine;
    private final NavigationManager navigationManager;

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
    private Label cubsLabel;
    @FXML
    private Label lastChallenge;
    @FXML
    private Button escButton;

    public NestController(GameEngine gameEngine, NavigationManager navigationManager) {
        this.gameEngine = gameEngine;
        this.navigationManager = navigationManager;
    }

    @FXML
    public void initialize(){
        saveButton.setOnAction(event -> handleSave());
        loadButton.setOnAction(event -> navigationManager.goToSaves(true));
        challengeButton.setOnAction(event -> navigationManager.goToChallenge());
        escButton.setOnAction(event -> navigationManager.goToStartMenu());
        showStats();
    }
    private void handleSave(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Salva Partita");
        dialog.setHeaderText("Inserisci nome slot:");
        dialog.showAndWait().ifPresent(gameEngine::saveGame);
    }

    private void showStats(){
        Animal player = gameEngine.getPlayer();
        if (player == null) return;
        lifeBar.setProgress(player.getLife() / 100.0);
        energyBar.setProgress(player.getEnergy() / 100.0);
        staminaBar.setProgress(player.getStamina() / 100.0);
        animalTypeLabel.setText("Animale: " + player.getType());
        cubsLabel.setText("Prole: " + player.getCubs());
        lastChallenge.setText("Stage: " + gameEngine.getCurrentStage());
    }
}
