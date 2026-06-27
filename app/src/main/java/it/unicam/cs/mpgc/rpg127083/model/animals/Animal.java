package it.unicam.cs.mpgc.rpg127083.model.animals;

import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.Nest;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;

public abstract class Animal {
    private long id;
    public String name;
    public Habitat habitat;
    public double life;
    public double energy;
    public double stamina;
    public Nest nest;
    public Challenge currentChallenge;
}
