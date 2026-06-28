package it.unicam.cs.mpgc.rpg127083.model.challenge;

import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;

import java.util.List;

public class FirstChallenge extends Challenge {
    @Override
    public List<Choice> getChoices() {
        return List.of(
                new Choice("ACT", "Agisci"),
                new Choice("WAIT", "Aspetta e osserva")
        );
    }

    @Override
    public String executeChoice(Choice choice, Animal animal) {
        return switch(choice.getId()) {
            case "ACT" -> animal.handleActChoice(1);
            case "WAIT" -> animal.handleWaitChoice(1);
            default -> "Scelta non valida.";
        };
    }
}
