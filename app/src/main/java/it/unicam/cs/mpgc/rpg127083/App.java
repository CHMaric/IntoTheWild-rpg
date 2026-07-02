
package it.unicam.cs.mpgc.rpg127083;

import it.unicam.cs.mpgc.rpg127083.ui.controller.StartMenuController;
import it.unicam.cs.mpgc.rpg127083.core.GameEngine;
import it.unicam.cs.mpgc.rpg127083.model.habitats.factory.HabitatRegistry;
import it.unicam.cs.mpgc.rpg127083.model.habitats.factory.ItalianAlpsFactory;
import it.unicam.cs.mpgc.rpg127083.persistence.ChallengeLoader;
import it.unicam.cs.mpgc.rpg127083.persistence.JsonChallengeLoader;
import it.unicam.cs.mpgc.rpg127083.persistence.JsonSaveManager;
import it.unicam.cs.mpgc.rpg127083.persistence.SaveManager;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        SaveManager saveManager = new JsonSaveManager();
        ChallengeLoader challengeLoader = new JsonChallengeLoader();
        HabitatRegistry habitatRegistry = new HabitatRegistry();
        habitatRegistry.registerFactory("ITALIAN_ALPS", new ItalianAlpsFactory());
        GameEngine gameEngine =
                new GameEngine(null, challengeLoader, saveManager,habitatRegistry);

        SceneManager sceneManager = new SceneManager(primaryStage);
        StartMenuController startMenuController = new StartMenuController(gameEngine, sceneManager);
        primaryStage.setTitle("Into Te Wild");
        sceneManager.switchScene("/resources/view/StartMenuView.fxml", startMenuController);
    }

    public static void main(String[] args){
        launch(args);
    }
}