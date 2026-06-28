package it.unicam.cs.mpgc.rpg127083.persistence;

import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;

import java.io.IOException;
import java.util.List;

public interface SaveManager {
    void save(SaveData data, String filePath) throws IOException;
    SaveData load(String filePath) throws IOException;
    List<Challenge> loadChallengesForAnimal(String habitatType, String animalType);
}
