package it.unicam.cs.mpgc.rpg127083.core.model.animals;

import it.unicam.cs.mpgc.rpg127083.core.model.habitats.Habitat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Animal {
    private final long id;
    private final AnimalType type;
    private final Habitat habitat;
    private double life;
    private double energy;
    private double stamina;
    private int cubs;

    public Animal(long id, AnimalType type, Habitat habitat, double life, double energy, double stamina, int cubs) {
        this.id = id;
        this.type = type;
        this.habitat = habitat;
        this.life = life;
        this.energy = energy;
        this.stamina = stamina;
        this.cubs = cubs;
    }

    public void setLife(double life) {this.life = Math.clamp(life, 0, 100);}
    public void setEnergy(double energy) {this.energy = Math.clamp(energy, 0, 100);}
    public void setStamina(double stamina) {this.stamina = Math.clamp(stamina, 0, 100);}
    public void setCubs(int cubs){this.cubs = Math.max(0, cubs);
    }
}
