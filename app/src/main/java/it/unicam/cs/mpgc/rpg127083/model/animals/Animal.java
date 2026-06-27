package it.unicam.cs.mpgc.rpg127083.model.animals;

import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.Nest;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;
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
    private Nest nest;
    private Challenge currentChallenge;


    public abstract String getFirstChallengeDescription();
    public abstract String getSecondChallengeDescription();
    public abstract String getThirdChallengeDescription();

    public abstract String handleActChoice();
    public abstract String handleWaitChoice();

}
