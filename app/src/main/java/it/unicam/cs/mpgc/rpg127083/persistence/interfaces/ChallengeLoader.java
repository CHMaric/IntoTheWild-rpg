package it.unicam.cs.mpgc.rpg127083.persistence.interfaces;

import it.unicam.cs.mpgc.rpg127083.core.mechanics.Challenge;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.Habitat;
import java.util.List;

/**
 * Interface for loading challenges from a data source.
 * Implementations of this interface should provide the logic to retrieve
 * challenges based on the specified habitat type and animal type.
 */
public interface ChallengeLoader {
    List<Challenge> loadChallengesForAnimal(Habitat habitat, String animalType);
}
