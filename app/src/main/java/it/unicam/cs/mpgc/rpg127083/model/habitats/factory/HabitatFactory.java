package it.unicam.cs.mpgc.rpg127083.model.habitats.factory;

import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;

public interface HabitatFactory {
    Habitat createHabitat();
    Animal createAnimal(AnimalType animalType);
}
