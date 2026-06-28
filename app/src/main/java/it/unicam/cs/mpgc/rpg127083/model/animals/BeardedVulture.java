package it.unicam.cs.mpgc.rpg127083.model.animals;


import it.unicam.cs.mpgc.rpg127083.model.Nest;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;

public class BeardedVulture extends Animal {


    public BeardedVulture(long id, AnimalType type, Habitat habitat, double life, double energy, double stamina, Nest nest, Challenge currentChallenge) {
        super(id, type, habitat, life, energy, stamina, nest, currentChallenge);
    }

    @Override
    public String getChallengeDescription(int stage) {
        return switch(stage){
            case 1 -> "Le uova sono sensibili al freddo.";
            case 2 -> "Un predatore ha abbandonato una carcassa.";
            case 3 -> "Hai trovato una carcassa lasciata indietro da cacciatori umani.";
            default -> "Non ci sono sfide da affrontare";
        };
    }

    @Override
    public String handleActChoice(int stage) {
        return switch(stage){
            case 1 -> "Decidi di proteggere le uova dal freddo.";
            case 2 -> "Decidi di mangiare la carcassa per nutrirti.";
            case 3 -> "Decidi di mangiare la carcassa abbattuta dagli umani.";
            default -> "Non ci sono azioni disponibili.";
        };
    }

    @Override
    public String handleWaitChoice(int stage) {
        return switch(stage){
            case 1 -> "Decidi di aspettare che il freddo passi.";
            case 2 -> "Decidi di aspettare per paura che il predatore torni";
            case 3 -> "Decidi di non mangiare dalla carcassa sospetta";
            default -> "Non ci sono azioni disponibili.";
        };
    }
}
