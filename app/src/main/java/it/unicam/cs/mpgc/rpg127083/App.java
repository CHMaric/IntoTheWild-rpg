
package it.unicam.cs.mpgc.rpg127083;

import it.unicam.cs.mpgc.rpg127083.persistence.JsonFilePersistenceService;
import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.GamePersistenceService;
import it.unicam.cs.mpgc.rpg127083.ui.controller.StartMenuController;
import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.HabitatRegistry;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.ItalianAlpsFactory;
import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.ChallengeLoader;
import it.unicam.cs.mpgc.rpg127083.persistence.JsonChallengeLoader;
import it.unicam.cs.mpgc.rpg127083.persistence.JsonSaveManager;
import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.SaveManager;
import it.unicam.cs.mpgc.rpg127083.ui.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            ChallengeLoader challengeLoader = new JsonChallengeLoader();
            SaveManager saveManager = new JsonSaveManager();
            GamePersistenceService persistenceService = new JsonFilePersistenceService(saveManager);
            HabitatRegistry habitatRegistry = new HabitatRegistry();
            habitatRegistry.registerFactory("ITALIAN_ALPS", new ItalianAlpsFactory());

            GameEngine gameEngine =
                    new GameEngine(null, challengeLoader,
                            persistenceService, habitatRegistry);

            SceneManager sceneManager = new SceneManager(primaryStage);
            StartMenuController startMenuController = new StartMenuController(gameEngine, sceneManager);
            primaryStage.setTitle("Into Te Wild");
            sceneManager.switchScene("/view/StartMenuView.fxml", startMenuController);
        } catch ( Throwable t){
            System.err.println("=== CRASH RILEVATO ALL'AVVIO DI JAVAFX ===");
            t.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}