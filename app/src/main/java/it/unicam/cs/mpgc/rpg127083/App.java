
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

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            AppConfiguration appConfiguration = new AppConfiguration();
            appConfiguration.start(primaryStage);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}