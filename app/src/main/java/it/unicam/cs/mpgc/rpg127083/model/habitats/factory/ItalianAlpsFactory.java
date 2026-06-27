package it.unicam.cs.mpgc.rpg127083.model.habitats.factory;

import it.unicam.cs.mpgc.rpg127083.model.animals.*;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;
import it.unicam.cs.mpgc.rpg127083.model.habitats.ItalianAlps;

public class ItalianAlpsFactory implements HabitatFactory{

    @Override
    public Habitat createHabitat() {
        return new ItalianAlps();
    }

    @Override
    public Animal createAnimal(AnimalType animalType) {
        return switch(animalType) {
            case FOX -> new Fox();
            case WOLF -> new Wolf();
            case HARE -> new Hare();
            case BEARDED_VULTURE -> new BeardedVulture();
            default -> throw new IllegalArgumentException("Unknown animal type");
        };
    }
}
