package it.unicam.cs.mpgc.rpg127083.ui.component;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.Choice;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.ui.util.StatAnimationHelper;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class PlayerStatsView {
    private final ProgressBar lifeBar;
    private final ProgressBar energyBar;
    private final ProgressBar staminaBar;
    private final Label lifeBarLabel;
    private final Label energyBarLabel;
    private final Label staminaBarLabel;
    private final Label cubsLabel;
    private final Label cubsCounterLabel;

    public PlayerStatsView(ProgressBar lifeBar, ProgressBar energyBar, ProgressBar staminaBar,
                           Label lifeBarLabel, Label energyBarLabel, Label staminaBarLabel,
                           Label cubsLabel, Label cubsCounterLabel) {
        this.lifeBar = lifeBar;
        this.energyBar = energyBar;
        this.staminaBar = staminaBar;
        this.lifeBarLabel = lifeBarLabel;
        this.energyBarLabel = energyBarLabel;
        this.staminaBarLabel = staminaBarLabel;
        this.cubsLabel = cubsLabel;
        this.cubsCounterLabel = cubsCounterLabel;
    }

    public void updateStats(Animal animal) {
        if (animal == null) return;
        lifeBar.setProgress(animal.getLife() / 100.0);
        energyBar.setProgress(animal.getEnergy() / 100.0);
        staminaBar.setProgress(animal.getStamina() / 100.0);
        cubsLabel.setText("Prole: " + animal.getCubs());
    }

    public void animateEffects(Choice choice) {
        if (choice == null) return;
        StatAnimationHelper.animateSingleLabel(lifeBarLabel, choice.getLifeEffect());
        StatAnimationHelper.animateSingleLabel(energyBarLabel, choice.getEnergyEffect());
        StatAnimationHelper.animateSingleLabel(staminaBarLabel, choice.getStaminaEffect());
        StatAnimationHelper.animateSingleLabel(cubsCounterLabel, choice.getCubsEffect());
    }
}
