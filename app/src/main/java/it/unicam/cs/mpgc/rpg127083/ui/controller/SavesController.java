package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.util.FxAsync;
import it.unicam.cs.mpgc.rpg127083.ui.navigation.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.util.List;

public class SavesController {
    private final NavigationManager navigationManager;
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


    public SavesController(NavigationManager navigationManager, GameEngine gameEngine, boolean fromGame) {
        this.navigationManager = navigationManager;
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
        if(fromGame)
            navigationManager.goToNest();
        else
            navigationManager.goToStartMenu();
    }

    private void handleDelete() {
        String slotName = savesComboBox.getValue();
        if (slotName == null)
            return;
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
        if(slotName == null || slotName.isBlank())
            return;
        FxAsync.execute(
                gameEngine.loadGameAsync(slotName),
                navigationManager::goToNest,
                ex -> FxAsync.showErrorAlert("Errore Caricamento", ex)
        );
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
