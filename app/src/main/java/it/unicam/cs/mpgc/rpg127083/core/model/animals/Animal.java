package it.unicam.cs.mpgc.rpg127083.core.model.animals;

import it.unicam.cs.mpgc.rpg127083.core.model.habitats.Habitat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Animal {
    private long id;
    private AnimalType type;
    private Habitat habitat;
    private double life;
    private double energy;
    private double stamina;

    public void setLife(double life) {this.life = Math.clamp(life, 0, 100);}
    public void setEnergy(double energy) {this.energy = Math.clamp(energy, 0, 100);}
    public void setStamina(double stamina) {this.stamina = Math.clamp(stamina, 0, 100);}
}
