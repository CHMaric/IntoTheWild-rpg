package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.util.List;

public class SavesController {
    private final SceneManager sceneManager;
    private final GameEngine gameEngine;
    private final boolean fromGame;

    @FXML
    private Button loadButton;
    @FXML
    private Button delButton;
    @FXML
    private Button goBackButton;
    @FXML
    private ComboBox<String> savesComboBox;


    public SavesController(SceneManager sceneManager, GameEngine gameEngine, boolean fromGame) {
        this.sceneManager = sceneManager;
        this.gameEngine = gameEngine;
        this.fromGame = fromGame;
    }
    
    public void initialize(){
        updateList();
        loadButton.setOnAction(event -> handleLoad());
        delButton.setOnAction(event -> handleDelete());
        goBackButton.setOnAction(event -> handleGoBack());

        loadButton.setDisable(true);
        delButton.setDisable(true);
        savesComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean hasSelection = (newVal != null && !newVal.isBlank());
            loadButton.setDisable(!hasSelection);
            delButton.setDisable(!hasSelection);
        });
    }

    private void handleGoBack() {
        if(fromGame) {
            NestController nestController = new NestController(gameEngine, sceneManager);
            sceneManager.switchScene("/view/NestView.fxml", nestController);
        }
        else {
            StartMenuController startMenuController = new StartMenuController(gameEngine, sceneManager);
            sceneManager.switchScene("/view/StartMenuView.fxml", startMenuController);
        }
    }

    private void handleDelete() {
        String slotName = savesComboBox.getValue();
        if (slotName == null) {
            showAlert(Alert.AlertType.ERROR, "Salvataggio non selezionato");
            return;
        }
        boolean success = gameEngine.deleteSavedGame(slotName);
        if(success) {
            showAlert(Alert.AlertType.INFORMATION, "Salvataggio eliminato: " + slotName);
            updateList();
        }
        else
            showAlert(Alert.AlertType.ERROR, "Impossibile eliminare il salvataggio: " + slotName);
    }


    private void handleLoad() {
        String slotName = savesComboBox.getValue();
        if(slotName == null) {
            showAlert(Alert.AlertType.INFORMATION, "Seleziona un salvataggio");
            return;
        }
        boolean success = gameEngine.loadGame(slotName);
        if (success) {
            NestController nestController = new NestController(gameEngine, sceneManager);
            sceneManager.switchScene("/view/NestView.fxml", nestController);
        } else
            showAlert(Alert.AlertType.ERROR, "Impossibile caricare il file: " + slotName);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(type == Alert.AlertType.ERROR ? "Errore" : "Informazione");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateList() {
        List<String> saves = gameEngine.getAvailableSaveSlots();
        savesComboBox.getItems().setAll(saves);
        savesComboBox.getSelectionModel().clearSelection();
    }
}
