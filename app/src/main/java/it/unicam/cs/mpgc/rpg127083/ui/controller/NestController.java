package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
/*
    @FXML
    public void initialize(){
        saveButton.setOnAction(event -> gameEngine.saveGame());
        leaveButton.setOnAction(event -> {
            ChallengeController challengeController = new ChallengeController(gameEngine, sceneManager);
            sceneManager.switchScene("/resources/view/ChallengeView.fxml", challengeController);
        });
    }

 */
}
