package it.unicam.cs.mpgc.rpg127083.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private final Stage primaryStage;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void switchScene(String fxmlPath, Object controller) {
        try {
            System.out.println("FXML PATH: " + fxmlPath);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            if (controller != null) {
                loader.setController(controller);
            }
            Parent root = loader.load();
            System.out.println("ROOT LOADED: " + root);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            System.out.println("SCENE SET COMPLETATA");
        } catch (Exception e) {
            System.err.println("FATAL ERROR LOADING SCENE: " + fxmlPath);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

