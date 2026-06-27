package it.unicam.cs.mpgc.rpg127083.model.animals;


import it.unicam.cs.mpgc.rpg127083.model.Nest;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;

public class BeardedVulture extends Animal{


    public BeardedVulture(long id, AnimalType type, Habitat habitat, double life, double energy, double stamina, Nest nest, Challenge currentChallenge) {
        super(id, type, habitat, life, energy, stamina, nest, currentChallenge);
    }

    @Override
    public String getFirstChallengeDescription() {
        return "";
    }

    @Override
    public String getSecondChallengeDescription() {
        return "";
    }

    @Override
    public String getThirdChallengeDescription() {
        return "";
    }
}
