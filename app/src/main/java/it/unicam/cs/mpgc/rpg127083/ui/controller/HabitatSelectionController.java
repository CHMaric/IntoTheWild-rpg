package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.HabitatRegistry;
import it.unicam.cs.mpgc.rpg127083.ui.navigation.NavigationManager;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.io.InputStream;


public class HabitatSelectionController {
    private final HabitatRegistry habitatRegistry;
    private final NavigationManager navigationManager;

    @FXML
    private TilePane habitatContainer;


    public HabitatSelectionController(HabitatRegistry habitatRegistry, NavigationManager navigationManager) {
        this.habitatRegistry = habitatRegistry;
        this.navigationManager = navigationManager;
    }

    @FXML
    public void initialize() {
        habitatRegistry.getRegisteredHabitats().stream()
                .map(this::createHabitatButton)
                .forEach(habitatContainer.getChildren()::add);
    }
    private Button createHabitatButton(String habitatKey) {
        Button button = new Button(formatLabel(habitatKey));
        button.setPrefSize(200, 180);
        button.setContentDisplay(ContentDisplay.TOP);
        button.setAlignment(Pos.CENTER);
        button.getStyleClass().add("habitat-card-button");

        ImageView imageView = createHabitatImageView(habitatKey);
        if (imageView != null) {
            button.setGraphic(imageView);
        }
        button.setOnAction(event -> navigationManager.goToAnimalSelection(habitatKey));
        return button;
    }

    /**
     * Create the ImageView for the habitat following a chosen naming convention:
     * /images/{habitatKey in all caps}.jpg
     * es. ITALIAN_ALPS -> /images/italian_alps.jpg
     */
    private ImageView createHabitatImageView(String habitatKey) {
        String imagePath = "/images/" + habitatKey.toLowerCase() + "_background.jpg";
        InputStream is = getClass().getResourceAsStream(imagePath);
        if (is == null) {
            return null;
        }
        ImageView imgView = new ImageView(new Image(is));
        imgView.setFitWidth(190);
        imgView.setFitHeight(136);
        imgView.setPickOnBounds(true);
        return imgView;
    }

    private String formatLabel(String rawKey) {
        return rawKey.replace("_", " ");
    }
}
