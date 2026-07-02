package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class HabitatSelectionController {
    private final GameEngine gameEngine;
    private final SceneManager sceneManager;

    @FXML
    private Button alpsButton;

    public HabitatSelectionController(GameEngine gameEngine, SceneManager sceneManager) {
        this.gameEngine = gameEngine;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        alpsButton.setOnAction(event -> selectHabitat("ITALIAN_ALPS"));
    }

    private void selectHabitat(String habitat) {
        AnimalSelectionController animalController = new AnimalSelectionController(gameEngine, sceneManager, habitat);
        sceneManager.switchScene("/resources/view/AnimalSelectionView.fxml", animalController);
    }
}
