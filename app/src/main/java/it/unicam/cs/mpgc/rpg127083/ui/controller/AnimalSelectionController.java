package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.HabitatFactory;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.HabitatRegistry;
import it.unicam.cs.mpgc.rpg127083.ui.util.FxAsync;
import it.unicam.cs.mpgc.rpg127083.ui.navigation.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AnimalSelectionController {
    private final GameEngine gameEngine;
    private final NavigationManager navigationManager;
    private final String selectedHabitat;
    private final HabitatRegistry habitatRegistry;

    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private Button button4;

    @FXML private StackPane rootPane;

    public AnimalSelectionController(GameEngine gameEngine, NavigationManager navigationManager, String selectedHabitat, HabitatRegistry habitatRegistry) {
        this.gameEngine = gameEngine;
        this.navigationManager = navigationManager;
        this.selectedHabitat = selectedHabitat;
        this.habitatRegistry = habitatRegistry;
    }

    @FXML
    public void initialize() {
        applyDynamicBackground();
        gameEngine.initializeHabitat(selectedHabitat);
        HabitatFactory factory = habitatRegistry.getFactory(selectedHabitat);
        List<AnimalType> animals = new ArrayList<>(factory.getSupportedAnimalTypes());
        configureButton(button1, animals.get(0));
        configureButton(button2, animals.get(1));
        configureButton(button3, animals.get(2));
        configureButton(button4, animals.get(3));
    }

    private void applyDynamicBackground() {
        String imagePath = "/images/" + selectedHabitat.toLowerCase() + "_background.jpg";
        InputStream is = getClass().getResourceAsStream(imagePath);
        if (is != null) {
            rootPane.setStyle(
                    "-fx-background-image: url('" + getClass().getResource(imagePath).toExternalForm() + "');" +
                            "-fx-background-repeat: no-repeat;" +
                            "-fx-background-position: center center;" +
                            "-fx-background-size: cover;"
            );
        }
    }

    private void configureButton(Button button, AnimalType animalType) {
        button.setText(formatLabel(animalType.name()));

        ImageView iv = createAnimalImageView(animalType.name());
        if (iv != null) button.setGraphic(iv);

        button.setOnAction(event -> startGame(animalType));
    }

    private ImageView createAnimalImageView(String animalKey) {
        String imagePath = "/images/" + animalKey.toLowerCase() + ".jpg";
        InputStream is = getClass().getResourceAsStream(imagePath);
        if (is == null) {
            return null;
        }
        ImageView imgView = new ImageView(new Image(is));
        imgView.setFitWidth(145);
        imgView.setFitHeight(145);
        imgView.setPickOnBounds(true);
        return imgView;
    }

    private String formatLabel(String name) {
        String lower = name.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1).replace("_", " ");
    }

    private void startGame(AnimalType animalType) {
        setButtonsDisable(true);

        FxAsync.execute(
                gameEngine.startGameAsync(animalType),
                navigationManager::goToChallenge,
                ex -> {
                    setButtonsDisable(false);
                    FxAsync.showErrorAlert("Error starting game", ex);
                }
        );
    }

    private void setButtonsDisable(boolean disable) {
        button1.setDisable(disable);
        button2.setDisable(disable);
        button3.setDisable(disable);
        button4.setDisable(disable);
    }
}
