package it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory;

import it.unicam.cs.mpgc.rpg127083.core.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.Habitat;

/**
 * This interface allows for future extensions of the game's habitats and animals.
 * It defines methods for creating habitats and animals based on their types.
 */
public interface HabitatFactory {
    Habitat createHabitat();
    Animal createAnimal(AnimalType animalType);
}
