package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;

import java.util.List;
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
        List<String> availableSaves = gameEngine.getAvailableSaveSlots();

        if (availableSaves.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nessun Salvataggio");
            alert.setHeaderText(null);
            alert.setContentText("Non ci sono partite salvate al momento.");
            alert.showAndWait();
            return;
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>(availableSaves.get(0), availableSaves);
        dialog.setTitle("Carica Partita");
        dialog.setHeaderText("Seleziona lo slot di salvataggio da caricare:");
        dialog.setContentText("Salvataggio:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(slotName -> {
            boolean success = gameEngine.loadGame(slotName);
            if (success) {
                NestController nestController = new NestController(gameEngine, sceneManager);
                sceneManager.switchScene("/view/NestView.fxml", nestController);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errore di Caricamento");
                alert.setHeaderText(null);
                alert.setContentText("Impossibile caricare il file: " + slotName);
                alert.showAndWait();
            }
        });
    }

    private void exitGame(){
        Platform.exit();
        System.exit(0);
    }

}
