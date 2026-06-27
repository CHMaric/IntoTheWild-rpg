package it.unicam.cs.mpgc.rpg127083.model.animals;

import it.unicam.cs.mpgc.rpg127083.model.Nest;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;


public class Wolf extends Animal{


    public Wolf(long id, AnimalType type, Habitat habitat, double life, double energy, double stamina, Nest nest, Challenge currentChallenge) {
        super(id, type, habitat, life, energy, stamina, nest, currentChallenge);
    }

    @Override
    public String getFirstChallengeDescription() {
        return "Il braco ha bisogno di cibo per superare l'inverno.";
    }

    @Override
    public String getSecondChallengeDescription() {
        return "Il branco ha trovato tracce di attività umana.";
    }

    @Override
    public String getThirdChallengeDescription() {
        return "Un branco rivale reclama il nostro territorio.";
    }
}
