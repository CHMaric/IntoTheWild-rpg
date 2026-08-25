package it.unicam.cs.mpgc.rpg127083.ui.util;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class StatAnimationHelper {
    public static void animateSingleLabel(Label label, double effect) {
        if (label == null || effect == 0) return;
        formatTextLabelEffect(label, effect);
        handleFadeTransition(label);
    }

    private static void formatTextLabelEffect(Label label, double effect) {
        String text = (effect > 0 ? "+" : "") + (int) effect;
        label.setText(text);
        if (effect > 0)
            label.setTextFill(Color.web("#87CEEB"));
        else
            label.setTextFill(Color.web("#e74c3c"));
    }

    private static void handleFadeTransition(Label label) {
        label.setOpacity(1.0);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.30));
        FadeTransition fade = new FadeTransition(Duration.seconds(0.40), label);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        SequentialTransition sequence = new SequentialTransition(pause, fade);
        sequence.setOnFinished(e -> label.setText(""));
        sequence.play();
    }
}

