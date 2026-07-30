package it.unicam.cs.mpgc.rpg127083.core.mechanics;

import it.unicam.cs.mpgc.rpg127083.core.dto.ChoiceOutcome;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.Animal;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class Choice {
    private final String description;
    private final String outcomeDescription;
    private final double lifeEffect;
    private final double energyEffect;
    private final double staminaEffect;
    private final int cubsEffect;

    public Choice(String description, String outcomeDescription, double lifeEffect,
                  double energyEffect, double staminaEffect, int cubsEffect) {
        this.description = description;
        this.outcomeDescription = outcomeDescription;
        this.lifeEffect = lifeEffect;
        this.energyEffect = energyEffect;
        this.staminaEffect = staminaEffect;
        this.cubsEffect = cubsEffect;
    }

    public ChoiceOutcome applyEffects(@NonNull Animal animal) {
        animal.setLife(animal.getLife() + this.lifeEffect);
        animal.setEnergy(animal.getEnergy() + this.energyEffect);
        animal.setStamina(animal.getStamina() + this.staminaEffect);
        animal.setCubs(animal.getCubs()+this.cubsEffect);
        return new ChoiceOutcome(
                outcomeDescription,
                lifeEffect,
                energyEffect,
                staminaEffect,
                cubsEffect
        );
    }

}
