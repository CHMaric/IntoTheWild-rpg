package it.unicam.cs.mpgc.rpg127083.persistence;


import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;

import java.io.IOException;
import java.util.List;

public class JsonSaveManager implements SaveManager {

    @Override
    public void save(SaveData data, String filePath) throws IOException {

    }

    @Override
    public SaveData load(String filePath) throws IOException {
        return null;
    }

    @Override
    public List<Challenge> loadChallengesForAnimal(String habitatType, String animalType) {
        return List.of();
    }
}
