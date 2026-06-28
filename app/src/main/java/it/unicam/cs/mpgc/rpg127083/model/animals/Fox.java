package it.unicam.cs.mpgc.rpg127083.model.animals;


import it.unicam.cs.mpgc.rpg127083.model.Nest;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;

public class Fox extends Animal{
    public Fox(long id, AnimalType type, Habitat habitat, double life, double energy, double stamina, Nest nest, Challenge currentChallenge) {
        super(id, type, habitat, life, energy, stamina, nest, currentChallenge);
    }

    @Override
    public String getChallengeDescription(int stage) {
        return switch(stage){
            case 1 -> "Cerca del cibo per i cuccioli.";
            case 2 -> "Un orso sta passando vicino al tuo territorio.";
            case 3 -> "Hai avvistato dei cacciatori.";
            default -> "Non ci sono sfide da affrontare";
        };
    }

    @Override
    public String handleActChoice(int stage) {
        return switch(stage){
            case 1 -> "Decidi di lasciare il nido in cerca di cibo";
            case 2 -> "Decidi di lasciare il nido e perlustrare il territorio";
            case 3 -> "Decidi di lasciare il nido e cercare un posto sicuro per i tuoi cuccioli";
            default -> "Non ci sono azioni disponibili.";
        };
    }

    @Override
    public String handleWaitChoice(int stage) {
        return switch(stage){
            case 1 -> "Decidi di rimanere nel nido. I tuoi cuccioli sono sempre più affamati";
            case 2 -> "Decidi di rimanere nel nido per evitare di incontrare l'orso";
            case 3 -> "Ignori la presenza di umani. Uno dei tuoi cuccioli viene ucciso dai cacciatori.";
            default -> "Non ci sono azioni disponibili.";
        };
    }

}
