package it.unicam.cs.mpgc.rpg127083.core;

import it.unicam.cs.mpgc.rpg127083.model.animals.Animal;
import it.unicam.cs.mpgc.rpg127083.model.animals.AnimalType;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.factory.HabitatFactory;
import it.unicam.cs.mpgc.rpg127083.persistence.ChallengeLoader;
import it.unicam.cs.mpgc.rpg127083.persistence.JsonChallengeLoader;
import it.unicam.cs.mpgc.rpg127083.persistence.JsonSaveManager;
import it.unicam.cs.mpgc.rpg127083.persistence.SaveManager;

import java.util.List;

public class GameEngine {

    private final ChallengeLoader challengeLoader;
    private final SaveManager saveManager;
    private final HabitatFactory habitatFactory;
    private Animal player;
    private List<Challenge> challenges;
    private int currentStage;

    public GameEngine(HabitatFactory habitatFactory){
        this.habitatFactory = habitatFactory;
        this.challengeLoader = new JsonChallengeLoader();
        this.saveManager = new JsonSaveManager();
    }

    public void startGame(AnimalType animalType){
        this.currentStage = 1;
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

    public void executeActChoice(){
        Challenge current = getCurrentChallenge();
        if(current != null){
            current.executeAct(player);
            currentStage++;
        }
    }

    public void executeWaitChoice(){
        Challenge current = getCurrentChallenge();
        if(current != null){
            current.executeWait(player);
            currentStage++;
        }
    }

    public GameState checkGameState(){
        if(player == null) return GameState.RUNNING;
        if(player.getLife() <= 0) return GameState.GAME_OVER;
        if(currentStage >= challenges.size()) return GameState.VICTORY;
        return GameState.RUNNING;
    }
}
