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
        Habitat h = this.createHabitat();

        return switch(animalType) {
            case FOX -> new Fox(0,AnimalType.FOX,h,85,80,75,null, null);
            case WOLF -> new Wolf(1,AnimalType.WOLF,h,100,100,100,null,null);
            case HARE -> new Hare(2, AnimalType.HARE, h, 50, 90, 70, null, null);
            case BEARDED_VULTURE -> new BeardedVulture(3, AnimalType.BEARDED_VULTURE, h, 90, 70, 80, null, null);
            default -> throw new IllegalArgumentException("Animal not present in this habitat");
        };
    }
}
