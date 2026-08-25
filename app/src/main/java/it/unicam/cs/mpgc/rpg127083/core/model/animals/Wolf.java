package it.unicam.cs.mpgc.rpg127083.core.model.animals;

import it.unicam.cs.mpgc.rpg127083.core.model.habitats.Habitat;


public class Wolf extends Animal{
    public Wolf(long id, Habitat habitat, double life,
                double energy, double stamina, int cubs) {
        super(id, AnimalType.WOLF, habitat, life, energy, stamina, cubs);
    }

}
