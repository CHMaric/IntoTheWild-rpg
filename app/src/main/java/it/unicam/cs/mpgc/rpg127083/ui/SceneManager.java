package it.unicam.cs.mpgc.rpg127083.ui;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.ui.controller.NestController;
import it.unicam.cs.mpgc.rpg127083.ui.controller.StartMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private final Stage primaryStage;
    private Scene scene;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void switchScene(String fxmlPath, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            if (controller != null)
                loader.setController(controller);
            Parent root = loader.load();
            scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToStartMenu(GameEngine gameEngine){
        StartMenuController startMenuController = new StartMenuController(gameEngine, this);
        switchScene("/view/StartMenuView.fxml", startMenuController);
    }
    public void switchToNest(GameEngine gameEngine){
        NestController nestController = new NestController(gameEngine, this);
        switchScene("/view/NestView.fxml", nestController);
    }
}

