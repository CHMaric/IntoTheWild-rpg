package it.unicam.cs.mpgc.rpg127083.model.habitats;

import it.unicam.cs.mpgc.rpg127083.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;

import java.util.Set;

public abstract class Habitat {
    private long id;
    private String name;

    public abstract Set<AnimalType> getAvailableAnimalTypes();

    public abstract Set<Challenge> getChallenges();

    public long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
}
