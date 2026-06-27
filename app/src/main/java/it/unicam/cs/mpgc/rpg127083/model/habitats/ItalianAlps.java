package it.unicam.cs.mpgc.rpg127083.model.habitats;

import it.unicam.cs.mpgc.rpg127083.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;

import java.util.Set;

public class ItalianAlps extends Habitat{

    @Override
    public Set<AnimalType> getAvailableAnimalTypes() {
        return Set.of(
                AnimalType.FOX,
                AnimalType.WOLF,
                AnimalType.BEARDED_VULTURE,
                AnimalType.HARE);
    }

    @Override
    public Set<Challenge> getChallenges() {
        return Set.of();
    }
}
