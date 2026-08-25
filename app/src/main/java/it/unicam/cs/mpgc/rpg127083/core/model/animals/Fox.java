package it.unicam.cs.mpgc.rpg127083.core.model.animals;


import it.unicam.cs.mpgc.rpg127083.core.model.habitats.Habitat;

public class Fox extends Animal{
    public Fox(long id, Habitat habitat, double life,
               double energy, double stamina, int cubs) {
        super(id, AnimalType.FOX, habitat, life, energy, stamina, cubs);
    }

}
