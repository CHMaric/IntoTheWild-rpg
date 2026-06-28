package it.unicam.cs.mpgc.rpg127083.model.challenge;

import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;


public class Choice {
    private final String description;
    private final String outcomeDescription;
    private final double lifeEffect;
    private final double energyEffect;
    private final double staminaEffect;

    public Choice(String description, String outcomeDescription, double lifeEffect,
                  double energyEffect, double staminaEffect) {
        this.description = description;
        this.outcomeDescription = outcomeDescription;
        this.lifeEffect = lifeEffect;
        this.energyEffect = energyEffect;
        this.staminaEffect = staminaEffect;
    }

    public void applyEffects(Animal animal) {
        animal.setLife(animal.getLife() + this.lifeEffect);
        animal.setEnergy(animal.getEnergy() + this.energyEffect);
        animal.setStamina(animal.getStamina() + this.staminaEffect);
    }

    public String getOutcomeDescription() {
        return outcomeDescription;
    }

    public String getDescription() {
        return description;
    }

}
