package it.unicam.cs.mpgc.rpg127083.model.animals;

import it.unicam.cs.mpgc.rpg127083.model.Nest;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;


public class Wolf extends Animal{


    public Wolf(long id, AnimalType type, Habitat habitat, double life, double energy, double stamina, Nest nest, Challenge currentChallenge) {
        super(id, type, habitat, life, energy, stamina, nest, currentChallenge);
    }

    @Override
    public String getChallengeDescription(int stage) {
        return switch(stage){
            case 1 -> "Il braco ha bisogno di cibo per superare l'inverno.";
            case 2 -> "Il branco ha trovato tracce di attività umana.";
            case 3 -> "Un branco rivale reclama il nostro territorio.";
            default -> "Non ci sono sfide da affrontare";
        };
    }

    @Override
    public String handleActChoice(int stage) {
        return switch(stage){
            case 1 -> "Decidi di andare a caccia. Catturi un cervo ma vieni ferito";
            case 2 -> "Decidi di osservare gli umani. Riesci a spaventarli";
            case 3 -> "Decidi di lottare contro il branco rivale.";
            default -> "Non ci sono azioni disponibili.";
        };
    }

    @Override
    public String handleWaitChoice(int stage) {
        return switch(stage){
            case 1 -> "Decidi di aspettare";
            case 2 -> "Decidi di nasconderti. La fame aumenta e sei sempre più debole";
            case 3 -> "La coppia alfa è stata sconfitta. Decidi di metterti in salvo";
            default -> "Non ci sono azioni disponibili.";
        };
    }

}
