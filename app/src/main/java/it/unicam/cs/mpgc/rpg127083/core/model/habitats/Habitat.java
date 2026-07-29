package it.unicam.cs.mpgc.rpg127083.core.model.habitats;

import it.unicam.cs.mpgc.rpg127083.core.model.animals.AnimalType;
import lombok.Getter;
import java.util.Set;

@Getter
public abstract class Habitat {
    private final long id;
    private final String label;
    private final Set<AnimalType> availableAnimalTypes;

    public Habitat(long id, String label, Set<AnimalType> availableAnimalTypes) {
        this.id = id;
        this.label = label;
        this.availableAnimalTypes = Set.copyOf(availableAnimalTypes);
    }
}
