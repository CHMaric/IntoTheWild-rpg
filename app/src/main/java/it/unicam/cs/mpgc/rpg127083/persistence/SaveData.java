package it.unicam.cs.mpgc.rpg127083.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveData {
    private long id;
    private String animalType;
    private String habitat;
    private double life;
    private double energy;
    private double stamina;
    private int currentStage;
}
