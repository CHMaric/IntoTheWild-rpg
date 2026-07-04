package it.unicam.cs.mpgc.rpg127083.core.mechanics;

import it.unicam.cs.mpgc.rpg127083.core.dto.ChoiceOutcome;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.core.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.HabitatFactory;
import it.unicam.cs.mpgc.rpg127083.core.model.habitats.factory.HabitatRegistry;
import it.unicam.cs.mpgc.rpg127083.persistence.*;
import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.ChallengeLoader;
import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.GamePersistenceService;
import lombok.Getter;

import java.io.IOException;
import java.util.List;

@Getter
public class GameEngine {

    private final ChallengeLoader challengeLoader;
    private final GamePersistenceService persistenceService;
    private HabitatFactory habitatFactory;
    private final HabitatRegistry habitatRegistry;
    private Animal player;
    private List<Challenge> challenges;
    private int currentStage;

    public GameEngine(HabitatFactory habitatFactory, ChallengeLoader challengeLoader,
                      GamePersistenceService persistenceService, HabitatRegistry habitatRegistry){
        this.habitatFactory = habitatFactory;
        this.challengeLoader = challengeLoader;
        this.persistenceService = persistenceService;
        this.habitatRegistry = habitatRegistry;
    }
    public void initializeHabitat(String habitat){
        this.habitatFactory = this.habitatRegistry.getFactory(habitat);
    }

    public void startGame(AnimalType animalType){
        if(this.habitatFactory == null)
            throw new IllegalStateException("Game can't start if a Habitat has not been chosen");
        this.currentStage = 0;
        this.player = habitatFactory.createAnimal(animalType);
        this.challenges = challengeLoader.loadChallengesForAnimal(player.getHabitat(), animalType.name());
        if(challenges.isEmpty())
            throw new IllegalStateException("No challenges found for the selected animal type.");
    }

    public Challenge getCurrentChallenge() {
        if (challenges == null || currentStage >= challenges.size())
            return null;
        return challenges.get(currentStage);
    }

    public ChoiceOutcome executeActChoice(){
        Challenge current = getCurrentChallenge();
        if(current == null)
            return null;
        ChoiceOutcome res = current.executeAct(player);
        currentStage++;
        return res;
    }

    public ChoiceOutcome executeWaitChoice(){
        Challenge current = getCurrentChallenge();
        if(current == null)
            return null;
        ChoiceOutcome res = current.executeWait(player);
        currentStage++;
        return res;
    }

    public GameState checkGameState(){
        if(player == null) return GameState.RUNNING;
        if(player.getLife() <= 0) return GameState.GAME_OVER;
        if(currentStage >= challenges.size()) return GameState.VICTORY;
        return GameState.RUNNING;
    }

    public void saveGame(String slotName) {
        try {
            SaveData data = new SaveData(this.player, this.currentStage);
            persistenceService.saveGame(data, slotName);
        } catch (IOException e) {
            throw new IllegalStateException("Can't save game", e);
        }
    }

    public boolean loadGame(String slotName) {
        try {
            SaveData data = this.persistenceService.loadGame(slotName);

            this.habitatFactory = habitatRegistry.getFactory(data.getHabitat());
            AnimalType type = AnimalType.valueOf(data.getAnimalType());
            this.player = habitatFactory.createAnimal(type);
            data.restorePlayerState(this.player);
            this.currentStage = data.getCurrentStage();
            this.challenges = challengeLoader.loadChallengesForAnimal(player.getHabitat(), type.name());
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
