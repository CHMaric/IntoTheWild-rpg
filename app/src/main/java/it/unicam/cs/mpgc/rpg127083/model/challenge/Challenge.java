package it.unicam.cs.mpgc.rpg127083.model.challenge;

import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;

import java.util.List;

public abstract class Challenge {
    public abstract List<Choice> getChoices();
    public abstract String executeChoice(Choice choice, Animal animal);
}
