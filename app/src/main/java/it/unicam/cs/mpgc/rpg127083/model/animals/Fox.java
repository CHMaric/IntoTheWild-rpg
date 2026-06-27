package it.unicam.cs.mpgc.rpg127083.model.animals;


import it.unicam.cs.mpgc.rpg127083.model.Nest;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;

public class Fox extends Animal{
    public Fox(long id, AnimalType type, Habitat habitat, double life, double energy, double stamina, Nest nest, Challenge currentChallenge) {
        super(id, type, habitat, life, energy, stamina, nest, currentChallenge);
    }

    @Override
    public String getFirstChallengeDescription() {
        return "Cerca del cibo per i cuccioli.";
    }

    @Override
    public String getSecondChallengeDescription() {
        return "Un orso sta passando vicino al tuo territorio.";
    }

    @Override
    public String getThirdChallengeDescription() {
        return "Hai avvistato dei cacciatori.";
    }
}
