package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.navigation.NavigationManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartMenuController {
    private final GameEngine gameEngine;
    private final NavigationManager navigationManager;
    @FXML
    private Button newGameButton;
    @FXML
    private Button loadGameButton;
    @FXML
    private Button escButton;

    public StartMenuController(GameEngine gameEngine, NavigationManager navigationManager) {
        this.gameEngine = gameEngine;
        this.navigationManager = navigationManager;
    }

    @FXML
    public void initialize(){
        newGameButton.setOnAction(event -> navigationManager.goToHabitatSelection());
        loadGameButton.setOnAction(event -> navigationManager.goToSaves(false));
        escButton.setOnAction(event -> exitGame());
    }
    private void exitGame(){
        Platform.exit();
        System.exit(0);
    }
}
