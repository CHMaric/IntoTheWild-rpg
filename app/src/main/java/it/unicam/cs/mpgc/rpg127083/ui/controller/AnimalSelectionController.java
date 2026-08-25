package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.ui.util.FxAsync;
import it.unicam.cs.mpgc.rpg127083.ui.navigation.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AnimalSelectionController {
    private final GameEngine gameEngine;
    private final NavigationManager navigationManager;
    private final String selectedHabitat;

    @FXML
    private Button wolfButton;
    @FXML
    private Button foxButton;
    @FXML
    private Button hareButton;
    @FXML
    private Button vultureButton;

    public AnimalSelectionController(GameEngine gameEngine, NavigationManager navigationManager, String selectedHabitat) {
        this.gameEngine = gameEngine;
        this.navigationManager = navigationManager;
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
        setButtonsDisable(true);

        FxAsync.execute(
                gameEngine.startGameAsync(animalType),
                navigationManager::goToChallenge,
                ex -> {
                    setButtonsDisable(false);
                    FxAsync.showErrorAlert("Error starting game", ex);
                }
        );
    }

    private void setButtonsDisable(boolean disable) {
        wolfButton.setDisable(disable);
        foxButton.setDisable(disable);
        hareButton.setDisable(disable);
        vultureButton.setDisable(disable);
    }
}
