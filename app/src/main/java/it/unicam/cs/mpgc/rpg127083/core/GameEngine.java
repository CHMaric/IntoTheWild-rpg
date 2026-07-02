package it.unicam.cs.mpgc.rpg127083.core;

import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.factory.HabitatFactory;
import it.unicam.cs.mpgc.rpg127083.model.habitats.factory.HabitatRegistry;
import it.unicam.cs.mpgc.rpg127083.persistence.*;

import java.io.IOException;
import java.util.List;

public class GameEngine {

    private final ChallengeLoader challengeLoader;
    private final SaveManager saveManager;
    private HabitatFactory habitatFactory;
    private final HabitatRegistry habitatRegistry;
    private Animal player;
    private List<Challenge> challenges;
    private int currentStage;

    public GameEngine(HabitatFactory habitatFactory, ChallengeLoader challengeLoader,
                      SaveManager saveManager, HabitatRegistry habitatRegistry){
        this.habitatFactory = habitatFactory;
        this.challengeLoader = challengeLoader;
        this.saveManager = saveManager;
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

    public String executeActChoice(){
        Challenge current = getCurrentChallenge();
        if(current != null){
            current.executeAct(player);
            String outcome = current.getActOutcome();
            currentStage++;
            return outcome;
        }
        return "";
    }

    public String executeWaitChoice(){
        Challenge current = getCurrentChallenge();
        if(current != null){
            current.executeWait(player);
            String outcome = current.getWaitOutcome();
            currentStage++;
            return outcome;
        }
        return "";
    }

    public GameState checkGameState(){
        if(player == null) return GameState.RUNNING;
        if(player.getLife() <= 0) return GameState.GAME_OVER;
        if(currentStage >= challenges.size()) return GameState.VICTORY;
        return GameState.RUNNING;
    }

    public void saveGame(String filePath) throws IOException {
        if(player == null)
            throw new IllegalArgumentException("Player can't be null");
        SaveData toSave = new SaveData(this.player, this.currentStage);
        this.saveManager.save(toSave,filePath);
    }

    public void loadGame(String filePath) throws IOException {
        SaveData data = this.saveManager.load(filePath);
        this.habitatFactory = habitatRegistry.getFactory(data.getHabitat());
        AnimalType type = AnimalType.valueOf(data.getAnimalType());
        this.player = habitatFactory.createAnimal(type);
        data.restorePlayerState(this.player);
        this.currentStage = data.getCurrentStage();
        this.challenges = challengeLoader.loadChallengesForAnimal(player.getHabitat(), type.name());
    }
}
