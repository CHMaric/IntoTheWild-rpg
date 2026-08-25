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
import java.util.concurrent.CompletableFuture;

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

    public CompletableFuture<Void> startGameAsync(AnimalType animalType){
        if(this.habitatFactory == null)
            throw new IllegalStateException("Game can't start if a Habitat has not been chosen");
        this.currentStage = 0;
        this.player = habitatFactory.createAnimal(animalType);

        return challengeLoader.loadChallengesForAnimalAsync(player.getHabitat(), animalType.name())
                .thenAccept(loadedChallenges -> {
                    if(loadedChallenges == null ||loadedChallenges.isEmpty())
                        throw new IllegalStateException("No challenges found for the selected animal type.");
                    this.challenges = loadedChallenges;
                });
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

    public CompletableFuture<Void> saveGameAsync(String slotName) {
        SaveData data = new SaveData(this.player, this.currentStage);
        return persistenceService.saveGameAsync(data, slotName);
    }

    public CompletableFuture<Void> loadGameAsync(String slotName) {
        return persistenceService.loadGameAsync(slotName)
                .thenAccept(this::restoreGame);
    }

    private void restoreGame(SaveData data){
        this.habitatFactory = habitatRegistry.getFactory(data.getHabitat());
        AnimalType type = AnimalType.valueOf(data.getAnimalType());
        this.player = habitatFactory.createAnimal(type);
        data.restorePlayerState(this.player);
        this.currentStage = data.getCurrentStage();
        //since the method is called by loadGameAsync, it's already runningin a thread in background,
        // so the synchronous version of loadChallengesForAnimal can be called
        this.challenges = challengeLoader.loadChallengesForAnimal(player.getHabitat(), type.name());
    }

    public List<String> getAvailableSaveSlots() {
        return persistenceService.getAvailableSlots();
    }

    public boolean deleteSavedGame(String slotName){
        try {
            persistenceService.deleteSave(slotName);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
