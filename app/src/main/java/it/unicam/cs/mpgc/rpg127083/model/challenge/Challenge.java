package it.unicam.cs.mpgc.rpg127083.model.challenge;

import it.unicam.cs.mpgc.rpg127083.core.dto.ChoiceOutcome;
import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;
import lombok.Getter;

@Getter
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
    public ChoiceOutcome executeAct(Animal animal){
        return actChoice.applyEffects(animal);
    }
    public ChoiceOutcome executeWait(Animal animal){
        return waitChoice.applyEffects(animal);
    }
}

