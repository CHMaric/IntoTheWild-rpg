package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.GameEngine;
import it.unicam.cs.mpgc.rpg127083.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AnimalSelectionController {
    private final GameEngine gameEngine;
    private final SceneManager sceneManager;
    private final String selectedHabitat;

    @FXML
    private Button wolfButton;
    @FXML
    private Button foxButton;
    @FXML
    private Button hareButton;
    @FXML
    private Button vultureButton;

    public AnimalSelectionController(GameEngine gameEngine, SceneManager sceneManager, String selectedHabitat) {
        this.gameEngine = gameEngine;
        this.sceneManager = sceneManager;
        this.selectedHabitat = selectedHabitat;
    }

    @FXML
    public void initialize() {
        wolfButton.setOnAction(event -> startGame(AnimalType.WOLF));
        foxButton.setOnAction(event -> startGame(AnimalType.FOX));
        hareButton.setOnAction(event -> startGame(AnimalType.HARE));
        vultureButton.setOnAction(event -> startGame(AnimalType.BEARDED_VULTURE));
    }

    private void startGame(AnimalType animalType) {
        gameEngine.initializeHabitat(selectedHabitat);
        gameEngine.startGame(animalType);
        ChallengeController challengeController = new ChallengeController(gameEngine, sceneManager);
        sceneManager.switchScene("/resources/view/ChallengeView.fxml", challengeController);
    }

}
