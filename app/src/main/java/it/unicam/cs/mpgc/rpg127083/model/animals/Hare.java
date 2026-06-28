package it.unicam.cs.mpgc.rpg127083.model.animals;

import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;


public class Hare extends Animal{
    public Hare(long id, AnimalType type, Habitat habitat, double life, double energy, double stamina, Challenge currentChallenge) {
        super(id, type, habitat, life, energy, stamina, currentChallenge);
    }

}
