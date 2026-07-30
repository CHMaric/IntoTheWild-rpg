package it.unicam.cs.mpgc.rpg127083.ui;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.controller.*;

public class NavigationManager {
    private final GameEngine gameEngine;
    private final SceneManager navigationManager;

    public NavigationManager(GameEngine gameEngine, SceneManager navigationManager) {
        this.gameEngine = gameEngine;
        this.navigationManager = navigationManager;
    }
    public void goToStartMenu() {
        StartMenuController controller = new StartMenuController(gameEngine, this);
        navigationManager.switchScene("/view/StartMenuView.fxml", controller);
    }

    public void goToHabitatSelection() {
        HabitatSelectionController controller = new HabitatSelectionController(gameEngine, this);
        navigationManager.switchScene("/view/HabitatSelectionView.fxml", controller);
    }

    public void goToAnimalSelection(String selectedHabitat) {
        AnimalSelectionController controller = new AnimalSelectionController(gameEngine, this, selectedHabitat);
        navigationManager.switchScene("/view/AnimalSelectionView.fxml", controller);
    }

    public void goToChallenge() {
        ChallengeController controller = new ChallengeController(gameEngine, this);
        navigationManager.switchScene("/view/ChallengeView.fxml", controller);
    }

    public void goToNest() {
        NestController controller = new NestController(gameEngine, this);
        navigationManager.switchScene("/view/NestView.fxml", controller);
    }

    public void goToSaves(boolean fromGame) {
        SavesController controller = new SavesController(this, gameEngine, fromGame);
        navigationManager.switchScene("/view/SavesView.fxml", controller);
    }
}
