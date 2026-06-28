package it.unicam.cs.mpgc.rpg127083.model.challenge;

import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;

public class Challenge {
    private final int stage;
    private final String description;
    private final Choice actChoice;
    private final Choice waitChoice;

    public Challenge(int stage, String description, Choice actChoice, Choice waitChoice) {
        this.stage = stage;
        this.description = description;
        this.actChoice = actChoice;
        this.waitChoice = waitChoice;
    }

    public void executeAct(Animal animal) {
        actChoice.applyEffects(animal);
    }

    public void executeWait(Animal animal) {
        waitChoice.applyEffects(animal);
    }

    public String getDescription() { return description; }
    public String getActOutcome() { return actChoice.getOutcomeDescription(); }
    public String getWaitOutcome() { return waitChoice.getOutcomeDescription(); }
    public int getStage() { return stage; }
}

