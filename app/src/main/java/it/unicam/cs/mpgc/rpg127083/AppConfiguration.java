package it.unicam.cs.mpgc.rpg127083;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.HabitatRegistry;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.ItalianAlpsFactory;
import it.unicam.cs.mpgc.rpg127083.persistence.JsonChallengeLoader;
import it.unicam.cs.mpgc.rpg127083.persistence.JsonFilePersistenceService;
import it.unicam.cs.mpgc.rpg127083.persistence.JsonSaveManager;
import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.ChallengeLoader;
import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.GamePersistenceService;
import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.SaveManager;
import it.unicam.cs.mpgc.rpg127083.ui.navigation.NavigationManager;
import it.unicam.cs.mpgc.rpg127083.ui.navigation.SceneManager;
import javafx.stage.Stage;

public class AppConfiguration {

    public void configureAndStart(Stage primaryStage) {

            ChallengeLoader challengeLoader = new JsonChallengeLoader();
            SaveManager saveManager = new JsonSaveManager();
            GamePersistenceService persistenceService = new JsonFilePersistenceService(saveManager);
            HabitatRegistry habitatRegistry = new HabitatRegistry();
            habitatRegistry.registerFactory("ITALIAN_ALPS", new ItalianAlpsFactory());
            GameEngine gameEngine = new GameEngine(null, challengeLoader,
                            persistenceService, habitatRegistry);
            SceneManager sceneManager = new SceneManager(primaryStage);
            NavigationManager navigationManager = new NavigationManager(gameEngine, sceneManager, habitatRegistry);
            primaryStage.setTitle("Into The Wild");
            navigationManager.goToStartMenu();
    }
}
