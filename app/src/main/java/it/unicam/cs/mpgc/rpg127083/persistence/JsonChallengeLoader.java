package it.unicam.cs.mpgc.rpg127083.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg127083.model.challenge.Challenge;
import it.unicam.cs.mpgc.rpg127083.model.habitats.Habitat;
import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.ChallengeLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class JsonChallengeLoader implements ChallengeLoader {
    private final Gson gson;

    public JsonChallengeLoader() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public List<Challenge> loadChallengesForAnimal(Habitat habitat, String animalType) {
        String path = "/challenges/" + habitat.getLabel() + "_" + animalType.toUpperCase() + ".json";
        try (InputStream input = getClass().getResourceAsStream(path)) {
            if(input == null)
                throw new IllegalArgumentException("File not found");
            Reader reader = new InputStreamReader(input);
            Challenge[] array = gson.fromJson(reader, Challenge[].class);
            return array != null ? List.of(array) : List.of();
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading file", e);
        }
    }
}
