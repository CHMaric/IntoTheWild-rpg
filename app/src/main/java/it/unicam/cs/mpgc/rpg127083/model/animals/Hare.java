package it.unicam.cs.mpgc.rpg127083.model.animals;

import it.unicam.cs.mpgc.rpg127083.model.Nest;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;


public class Hare extends Animal{
    public Hare(long id, AnimalType type, Habitat habitat, double life, double energy, double stamina, Nest nest, Challenge currentChallenge) {
        super(id, type, habitat, life, energy, stamina, nest, currentChallenge);
    }

    @Override
    public String getFirstChallengeDescription() {
        return "Lascia il nido in cerca di cibo.";
    }

    @Override
    public String getSecondChallengeDescription() {
        return "Un predatore è vicino.";
    }

    @Override
    public String getThirdChallengeDescription() {
        return "Il nido è stato scoperto da un predatore.";
    }
}
