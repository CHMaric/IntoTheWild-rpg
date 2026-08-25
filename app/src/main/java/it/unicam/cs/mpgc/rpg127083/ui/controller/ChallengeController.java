package it.unicam.cs.mpgc.rpg127083.ui.controller;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.Choice;
import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameEngine;
import it.unicam.cs.mpgc.rpg127083.core.mechanics.GameState;
import it.unicam.cs.mpgc.rpg127083.core.dto.ChoiceOutcome;
import it.unicam.cs.mpgc.rpg127083.core.mechanics.Challenge;
import it.unicam.cs.mpgc.rpg127083.ui.component.PlayerStatsView;
import it.unicam.cs.mpgc.rpg127083.ui.navigation.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.util.function.Function;
import java.util.function.Supplier;

public class ChallengeController {
    private final GameEngine gameEngine;
    private final NavigationManager navigationManager;
    private PlayerStatsView statsView;

    @FXML
    private Button actButton, waitButton, nestButton, nextChallengeButton, backToMenu;
    @FXML
    private Label actLabel, waitLabel, challengeDescriptionLabel, outcomeLabel;
    @FXML
    private ProgressBar lifeBar, energyBar, staminaBar;
    @FXML
    private Label lifeBarLabel, energyBarLabel, staminaBarLabel, cubsLabel, cubsCounterLabel;


    public ChallengeController(GameEngine gameEngine, NavigationManager navigationManager) {
        this.gameEngine = gameEngine;
        this.navigationManager = navigationManager;
    }

    @FXML
    public void initialize(){
        initializeStatsView();
        actButton.setOnAction(event -> handleChoice(Challenge::getActChoice, gameEngine::executeActChoice));
        waitButton.setOnAction(event -> handleChoice(Challenge::getWaitChoice, gameEngine::executeWaitChoice));
        nestButton.setOnAction(event -> navigationManager.goToNest());
        nextChallengeButton.setOnAction(event -> updateChallengeUI());
        backToMenu.setOnAction(event -> navigationManager.goToStartMenu());
        updateChallengeUI();
    }
    private void initializeStatsView() {
        this.statsView = new PlayerStatsView(
                lifeBar, energyBar, staminaBar,
                lifeBarLabel, energyBarLabel, staminaBarLabel,
                cubsLabel, cubsCounterLabel
        );
    }

    private void handleChoice(Function<Challenge, Choice> choiceExtractor, Supplier<ChoiceOutcome> actionSupplier) {
        Challenge current = gameEngine.getCurrentChallenge();
        if (current != null) {
            statsView.animateEffects(choiceExtractor.apply(current));
        }
        ChoiceOutcome outcome = actionSupplier.get();
        if (outcome != null) {
            renderOutcome(outcome);
        }
    }

    private void updateChallengeUI() {
        Challenge current = gameEngine.getCurrentChallenge();
        if (current != null) {
            challengeDescriptionLabel.setText(current.getDescription());
            actLabel.setText(current.getActChoice().getDescription());
            waitLabel.setText(current.getWaitChoice().getDescription());
            setUiMode(UiMode.PLAYING);
        } else {
            renderGameEnd("SEI SOPRAVVISSUTO", "La natura non ti ha sopraffatto");
        }
        statsView.updateStats(gameEngine.getPlayer());
    }

    private void renderOutcome(ChoiceOutcome outcome) {
        statsView.updateStats(gameEngine.getPlayer());
        GameState state = gameEngine.checkGameState();

        if (state == GameState.GAME_OVER) {
            renderGameEnd("SEI MORTO", outcome.description() + "\nLa natura ha fatto il suo corso.");
        } else if (state == GameState.VICTORY) {
            renderGameEnd("SEI SOPRAVVISSUTO", outcome.description() + "\nLa natura non ti ha sopraffatto.");
        } else {
            outcomeLabel.setText(outcome.description());
            setUiMode(UiMode.OUTCOME_PENDING);
        }
    }

    private void renderGameEnd(String title, String message) {
        challengeDescriptionLabel.setText(title);
        outcomeLabel.setText(message);
        setUiMode(UiMode.GAME_OVER);
    }


    private enum UiMode { PLAYING, OUTCOME_PENDING, GAME_OVER }

    private void setUiMode(UiMode mode) {
        actButton.setDisable(mode != UiMode.PLAYING);
        waitButton.setDisable(mode != UiMode.PLAYING);
        setComponentVisibility(outcomeLabel, mode != UiMode.PLAYING);
        setComponentVisibility(nestButton, mode == UiMode.OUTCOME_PENDING);
        setComponentVisibility(nextChallengeButton, mode == UiMode.OUTCOME_PENDING);
        setComponentVisibility(backToMenu, mode == UiMode.GAME_OVER);
    }

    private void setComponentVisibility(Node node, boolean visible) {
        node.setVisible(visible);
        node.setManaged(visible);
    }
}
