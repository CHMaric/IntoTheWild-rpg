package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class HabitatSelectionController {
    private final GameEngine gameEngine;
    private final SceneManager sceneManager;

    @FXML
    private Button alpsButton;
    @FXML
    private Button savanaButton;
    @FXML
    private Button tundraButton;
    @FXML
    private Button jungleButton;


    public HabitatSelectionController(GameEngine gameEngine, SceneManager sceneManager) {
        this.gameEngine = gameEngine;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        alpsButton.setOnAction(event -> selectAlpsHabitat("ITALIAN_ALPS"));
        //for future expansions with new habitats, modify below with setOnAction + private methods to handle event
        savanaButton.setDisable(true);
        jungleButton.setDisable(true);
        tundraButton.setDisable(true);
    }

    private void selectAlpsHabitat(String habitat) {
        AnimalSelectionController animalController = new AnimalSelectionController(gameEngine, sceneManager, habitat);
        sceneManager.switchScene("/view/AnimalSelectionView.fxml", animalController);
    }
}
