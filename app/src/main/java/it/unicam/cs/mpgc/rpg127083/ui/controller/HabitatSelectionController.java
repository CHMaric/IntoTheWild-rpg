package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class HabitatSelectionController {
    private final GameEngine gameEngine;
    private final NavigationManager navigationManager;

    @FXML
    private Button alpsButton;
    @FXML
    private Button savanaButton;
    @FXML
    private Button tundraButton;
    @FXML
    private Button jungleButton;


    public HabitatSelectionController(GameEngine gameEngine, NavigationManager navigationManager) {
        this.gameEngine = gameEngine;
        this.navigationManager = navigationManager;
    }

    @FXML
    public void initialize() {
        alpsButton.setOnAction(event -> navigationManager.goToAnimalSelection("ITALIAN_ALPS"));

        //for future expansions with new habitats, modify below with setOnAction
        savanaButton.setDisable(true);
        jungleButton.setDisable(true);
        tundraButton.setDisable(true);
    }
}
