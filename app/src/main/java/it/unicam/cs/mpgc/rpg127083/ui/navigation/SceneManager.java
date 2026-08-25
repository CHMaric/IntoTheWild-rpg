package it.unicam.cs.mpgc.rpg127083.ui.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {
    private final Stage primaryStage;


    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void switchScene(String fxmlPath, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            if (controller != null)
                loader.setController(controller);
            Parent root = loader.load();
            if (primaryStage.getScene() == null) {
                primaryStage.setScene(new Scene(root, 800, 600));
            } else {
                primaryStage.getScene().setRoot(root);
            }
            primaryStage.show();
        } catch (IOException e) {
            throw new IllegalStateException("Impossibile caricare la vista FXML: " + fxmlPath, e);
        }
    }
}

