package it.unicam.cs.mpgc.rpg127083.model.challenge;

import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;

import java.util.List;

public class ThirdChallenge extends Challenge {
    public List<Choice> getChoices() {
        return List.of(
                new Choice("ACT", "Agisci"),
                new Choice("WAIT", "Aspetta e osserva")
        );
    }

    @Override
    public String executeChoice(Choice choice, Animal animal) {
        return switch(choice.getId()) {
            case "ACT" -> animal.handleActChoice(3);
            case "WAIT" -> animal.handleWaitChoice(3);
            default -> "Scelta non valida.";
        };
    }
}
