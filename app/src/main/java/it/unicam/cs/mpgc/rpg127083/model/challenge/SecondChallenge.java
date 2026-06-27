package it.unicam.cs.mpgc.rpg127083.model.challenge;

import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;

import java.util.List;

public class SecondChallenge extends Challenge {
    @Override
    public List<Choice> getChoices() {
        return List.of();
    }

    @Override
    public String executeChoice(Choice choice, Animal animal) {
        return "";
    }
}
