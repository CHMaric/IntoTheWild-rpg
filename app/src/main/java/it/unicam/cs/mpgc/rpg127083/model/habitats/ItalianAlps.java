package it.unicam.cs.mpgc.rpg127083.model.habitats;

import it.unicam.cs.mpgc.rpg127083.model.animals.AnimalType;
import java.util.Set;

public class ItalianAlps extends Habitat {

    public ItalianAlps() {
        super(1, "Alpi Italiane");
    }

    @Override
    public Set<AnimalType> getAvailableAnimalTypes() {
        return Set.of(
                AnimalType.FOX,
                AnimalType.WOLF,
                AnimalType.BEARDED_VULTURE,
                AnimalType.HARE);
    }
}
