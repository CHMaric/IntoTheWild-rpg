package it.unicam.cs.mpgc.rpg127083.core.model.animals;


import it.unicam.cs.mpgc.rpg127083.core.model.habitats.Habitat;

public class BeardedVulture extends Animal {
    public BeardedVulture(long id, Habitat habitat, double life,
                          double energy, double stamina, int cubs) {
        super(id, AnimalType.BEARDED_VULTURE, habitat, life, energy, stamina, cubs);
    }
}
