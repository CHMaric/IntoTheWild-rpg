package it.unicam.cs.mpgc.rpg127083.persistence;

import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SaveData {
    private long id;
    private String animalType;
    private String habitat;
    private double life;
    private double energy;
    private double stamina;
    private int currentStage;

    public SaveData(Animal player, int currentStage) {
        this.id = player.getId();
        this.animalType = player.getType().name();
        this.habitat = player.getHabitat().getLabel();
        this.life = player.getLife();
        this.energy = player.getEnergy();
        this.stamina = player.getStamina();
        this.currentStage = currentStage;
    }
    public void restorePlayerState(Animal player) {
        player.setId(this.id);
        player.setLife(this.life);
        player.setEnergy(this.energy);
        player.setStamina(this.stamina);
    }
}
