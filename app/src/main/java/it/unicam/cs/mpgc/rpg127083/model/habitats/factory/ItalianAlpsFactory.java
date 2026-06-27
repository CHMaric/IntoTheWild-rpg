package it.unicam.cs.mpgc.rpg127083.model.habitats.factory;

import it.unicam.cs.mpgc.rpg127083.model.animals.*;
import it.unicam.cs.mpgc.rpg127083.model.challenge.FirstChallenge;
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
            case FOX -> new Fox(0,AnimalType.FOX,new ItalianAlps(),80,80,70,null, new FirstChallenge());
            case WOLF -> new Wolf(1,AnimalType.WOLF,new ItalianAlps(),100,100,100,null,new FirstChallenge());
            case HARE -> new Hare(2, AnimalType.HARE, new ItalianAlps(), 100, 100, 100, null, new FirstChallenge());
            case BEARDED_VULTURE -> new BeardedVulture(3, AnimalType.BEARDED_VULTURE, new ItalianAlps(), 100, 100, 100, null, new FirstChallenge());
            default -> throw new IllegalArgumentException("Animal not present in this habitat");
        };
    }
}
