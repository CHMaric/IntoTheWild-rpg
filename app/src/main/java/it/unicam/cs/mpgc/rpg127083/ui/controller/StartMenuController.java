package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
        SavesController savesController = new SavesController(sceneManager, gameEngine, false);
        sceneManager.switchScene("/view/SavesView.fxml", savesController);
    }
    private void exitGame(){
        Platform.exit();
        System.exit(0);
    }

}
