package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;


public class StartMenuController {
    private final GameEngine gameEngine;
    private final SceneManager sceneManager;
    @FXML
    private Button newGameButton;
    @FXML
    private Button loadGameButton;
    @FXML
    private Button escButton;

    public StartMenuController(GameEngine gameEngine, SceneManager sceneManager) {
        this.gameEngine = gameEngine;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize(){
        newGameButton.setOnAction(event -> handleNewGame());
        loadGameButton.setOnAction(event -> handleLoadGame());
        escButton.setOnAction(event -> exitGame());
    }

    private void handleNewGame(){
        HabitatSelectionController habitatSelectionController =
                new HabitatSelectionController(gameEngine, sceneManager);
        sceneManager.switchScene("/view/HabitatSelectionView.fxml", habitatSelectionController);
    }

    private void handleLoadGame(){
        TextInputDialog dialog = new TextInputDialog("NuovoSalvataggio");
        dialog.setTitle("Carica Partita");
        dialog.setHeaderText("Inserisci il nome dello slot di salvataggio da caricare:");
        dialog.setContentText("Nome slot:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(slotName -> {
            if (!slotName.isBlank()) {
                boolean success = gameEngine.loadGame(slotName.trim());
                if (success) {
                    // Se il caricamento va a buon fine, andiamo alla schermata Nest
                    // Nota: ChallengeController gestisce solitamente il loop, passiamolo coerentemente
                    ChallengeController challengeController = new ChallengeController(gameEngine, sceneManager);
                    sceneManager.switchScene("/view/NestView.fxml", challengeController);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Errore di Caricamento");
                    alert.setHeaderText(null);
                    alert.setContentText("Impossibile trovare o caricare il file di salvataggio: " + slotName);
                    alert.showAndWait();
                }
            }
        });
    }

    private void exitGame(){
        Platform.exit();
        System.exit(0);
    }

}
