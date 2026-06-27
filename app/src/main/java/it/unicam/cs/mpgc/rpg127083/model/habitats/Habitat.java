package it.unicam.cs.mpgc.rpg127083.model.habitats;

import it.unicam.cs.mpgc.rpg127083.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import lombok.Getter;
import java.util.List;
import java.util.Set;

@Getter
public abstract class Habitat {
    private long id;
    private String name;

    public Habitat(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract Set<AnimalType> getAvailableAnimalTypes();

    public abstract List<Challenge> getChallenges();

}
