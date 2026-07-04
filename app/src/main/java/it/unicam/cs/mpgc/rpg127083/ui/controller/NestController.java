package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.Optional;

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
    private void handleSave(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Salva Partita");
        dialog.setHeaderText("Inserisci nome slot:");
        dialog.setContentText("Nome:");
        dialog.showAndWait().ifPresent(this::saveGame);
    }
    private void saveGame(String slotName) {
        if (slotName.isBlank()) {
            showAlert(Alert.AlertType.ERROR,"Nome slot non valido.");
            return;
        }
        try {
            gameEngine.saveGame(slotName);
            showAlert(Alert.AlertType.INFORMATION,"Partita salvata correttamente");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Errore nel salvataggio");
        }
    }

    private void handleLoad(){
        List<String> availableSaves = gameEngine.getAvailableSaveSlots();
        if (availableSaves.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Non ci sono partite salvate al momento.");
            return;
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(availableSaves.get(0), availableSaves);
        dialog.setTitle("Carica Partita");
        dialog.setHeaderText("Seleziona lo slot:");
        dialog.setContentText("Salvataggio:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(slotName ->{
            boolean success = gameEngine.loadGame(slotName);
            if (success) {
                showStats();
                showAlert(Alert.AlertType.INFORMATION, "Partita caricata correttamente");
                }
            else
                showAlert(Alert.AlertType.ERROR, "Impossibile caricare il file: " + slotName);
        });
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(type == Alert.AlertType.ERROR ? "Errore" : "Informazione");
        alert.setTitle(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
