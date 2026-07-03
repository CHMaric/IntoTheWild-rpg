package it.unicam.cs.mpgc.rpg127083.ui;

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

            if (scene == null) {
                scene = new Scene(root, 800, 600);
                primaryStage.setScene(scene);
            } else
                scene.setRoot(root);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

