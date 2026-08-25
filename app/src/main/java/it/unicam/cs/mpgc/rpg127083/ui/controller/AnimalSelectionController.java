package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.HabitatFactory;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.HabitatRegistry;
import it.unicam.cs.mpgc.rpg127083.ui.util.FxAsync;
import it.unicam.cs.mpgc.rpg127083.ui.navigation.NavigationManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.io.InputStream;
import java.util.List;

public class AnimalSelectionController {
    private final GameEngine gameEngine;
    private final NavigationManager navigationManager;
    private final String selectedHabitat;
    private final HabitatRegistry habitatRegistry;

    @FXML private StackPane rootPane;
    @FXML private GridPane animalContainer;

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
        populateAnimalSelection();
    }
    private void populateAnimalSelection(){
        HabitatFactory factory = habitatRegistry.getFactory(selectedHabitat);
        List<AnimalType> animals = factory.getSupportedAnimalTypes().stream()
                .sorted()
                .toList();

        for(int i = 0; i < animals.size(); i++){
            Button button = createAnimalButton(animals.get(i));
            GridPane.setColumnIndex(button, i % 2);
            GridPane.setRowIndex(button, i / 2);
            animalContainer.getChildren().add(button);
        }
    }

    private Button createAnimalButton(AnimalType animalType) {
        Button button = new Button(formatLabel(animalType.name()));
        button.getStyleClass().add("animal-card-button");
        button.setPrefSize(180, 180);
        button.setContentDisplay(ContentDisplay.TOP);
        button.setAlignment(Pos.CENTER);

        ImageView iv = createAnimalImageView(animalType.name());
        if (iv != null) button.setGraphic(iv);

        button.setOnAction(event -> startGame(animalType));
        return button;
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
        String formatted = name.toLowerCase().replace("_", " ");
        return Character.toUpperCase(formatted.charAt(0)) + formatted.substring(1);
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
        animalContainer.setDisable(disable);
    }
}
