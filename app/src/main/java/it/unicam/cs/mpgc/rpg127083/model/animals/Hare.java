package it.unicam.cs.mpgc.rpg127083.model.animals;

import it.unicam.cs.mpgc.rpg127083.model.Nest;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;


public class Hare extends Animal{
    public Hare(long id, AnimalType type, Habitat habitat, double life, double energy, double stamina, Nest nest, Challenge currentChallenge) {
        super(id, type, habitat, life, energy, stamina, nest, currentChallenge);
    }

    @Override
    public String getChallengeDescription(int stage) {
        return switch(stage){
            case 1 -> "Lascia il nido in cerca di cibo.";
            case 2 -> "Un predatore è vicino.";
            case 3 -> "Il nido è stato scoperto da un predatore.";
            default -> "Non ci sono sfide da affrontare";
        };
    }

    @Override
    public String handleActChoice(int stage) {
        return switch(stage){
            case 1 -> "Decidi di lasciare il nido in cerca di cibo";
            case 2 -> "Decidi di scappare attirando il predatore lontano dalla prole";
            case 3 -> "Decidi di abbandonare il nido lasciando la tua prole al proprio destino";
            default -> "Non ci sono azioni disponibili.";
        };
    }

    @Override
    public String handleWaitChoice(int stage) {
        return switch(stage){
            case 1 -> "Decidi di rimanere nel nido. I tuoi cuccioli sono sempre più affamati";
            case 2 -> "Decidi di mimetizzarti. Non sei stato scoperto, ma hai perso la tua prole";
            case 3 -> "Decidi di rimanere nel nido e sacrificarti per permettere alla tua prole di sopravvivere";
            default -> "Non ci sono azioni disponibili.";
        };
    }
}
