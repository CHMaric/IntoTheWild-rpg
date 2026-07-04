package it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory;

import it.unicam.cs.mpgc.rpg127083.core.model.animals.*;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.Habitat;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.ItalianAlps;

public class ItalianAlpsFactory implements HabitatFactory{

    @Override
    public Habitat createHabitat() {
        return new ItalianAlps();
    }

    @Override
    public Animal createAnimal(AnimalType animalType) {
        Habitat h = this.createHabitat();

        return switch(animalType) {
            case FOX -> new Fox(0,AnimalType.FOX,h,85,80,75);
            case WOLF -> new Wolf(1,AnimalType.WOLF,h,100,100,100);
            case HARE -> new Hare(2, AnimalType.HARE, h, 50, 90, 70);
            case BEARDED_VULTURE -> new BeardedVulture(3, AnimalType.BEARDED_VULTURE, h, 90, 70, 80);
            default -> throw new IllegalArgumentException("Animal not present in this habitat");
        };
    }
}
