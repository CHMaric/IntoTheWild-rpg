package it.unicam.cs.mpgc.rpg127083.ui;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.controller.*;

public class NavigationManager {
    private final GameEngine gameEngine;
    private final SceneManager sceneManager;

    public NavigationManager(GameEngine gameEngine, SceneManager sceneManager) {
        this.gameEngine = gameEngine;
        this.sceneManager = sceneManager;
    }
    public void goToStartMenu() {
        StartMenuController controller = new StartMenuController(gameEngine, this);
        sceneManager.switchScene("/view/StartMenuView.fxml", controller);
    }

    public void goToHabitatSelection() {
        HabitatSelectionController controller = new HabitatSelectionController(gameEngine, this);
        sceneManager.switchScene("/view/HabitatSelectionView.fxml", controller);
    }

    public void goToAnimalSelection(String selectedHabitat) {
        AnimalSelectionController controller = new AnimalSelectionController(gameEngine, this, selectedHabitat);
        sceneManager.switchScene("/view/AnimalSelectionView.fxml", controller);
    }

    public void goToChallenge() {
        ChallengeController controller = new ChallengeController(gameEngine, this);
        sceneManager.switchScene("/view/ChallengeView.fxml", controller);
    }

    public void goToNest() {
        NestController controller = new NestController(gameEngine, this);
        sceneManager.switchScene("/view/NestView.fxml", controller);
    }

    public void goToSaves(boolean fromGame) {
        SavesController controller = new SavesController(this, gameEngine, fromGame);
        sceneManager.switchScene("/view/SavesView.fxml", controller);
    }
}
