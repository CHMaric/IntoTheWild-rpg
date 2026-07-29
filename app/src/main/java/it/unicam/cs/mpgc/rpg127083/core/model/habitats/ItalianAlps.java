package it.unicam.cs.mpgc.rpg127083.core.model.habitats;

import it.unicam.cs.mpgc.rpg127083.core.model.animals.AnimalType;
import java.util.Set;

public class ItalianAlps extends Habitat {

    public ItalianAlps(Set<AnimalType> availableAnimalTypes) {
        super(1, "ITALIAN_ALPS", availableAnimalTypes);}
}
