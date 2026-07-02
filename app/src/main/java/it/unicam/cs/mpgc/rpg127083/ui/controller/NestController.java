package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class NestController {
    private final GameEngine gameEngine;
    private final SceneManager sceneManager;

    @FXML
    private Button saveButton;
    @FXML
    private Button leaveButton;

    public NestController(GameEngine gameEngine, SceneManager sceneManager) {
        this.gameEngine = gameEngine;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize(){
        saveButton.setOnAction(event -> handleSave());
        leaveButton.setOnAction(event -> {
            ChallengeController challengeController = new ChallengeController(gameEngine, sceneManager);
            sceneManager.switchScene("/view/ChallengeView.fxml", challengeController);
        });
    }
    private void handleSave(){
        TextInputDialog dialog = new TextInputDialog("Nuovo Salvataggio");
        dialog.setTitle("Salva Partita");
        dialog.setHeaderText("Salva la tua partita come: ");
        dialog.setContentText("Nome slot:");

        Optional<String> res = dialog.showAndWait();
        res.ifPresent(slotName -> {
            if(!slotName.isBlank()){
                try{
                    gameEngine.saveGame(slotName);
                    showInfoAlert("Parita salvata correttamente");
                    } catch (IllegalStateException e) {
                        showErrorAlert("Errore nel salvataggio della partita: " + e.getMessage());
                    }
                } else {
                showErrorAlert("Nome slot non valido. La partita non è stata salvata.");
            }
        });
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(message);
        alert.showAndWait();
    }
    private void showErrorAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(message);
        alert.showAndWait();
    }

}
