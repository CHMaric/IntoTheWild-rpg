package it.unicam.cs.mpgc.rpg127083.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class JsonSaveManager implements SaveManager {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void save(SaveData data, String filePath) throws IOException {
        if(data == null || filePath == null)
            throw new IllegalArgumentException("Data and file path cannot be null");
        try(FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        }
    }

    @Override
    public SaveData load(String filePath) throws IOException {
        if(filePath == null || filePath.isBlank())
            throw new IllegalArgumentException("File path cannot be null");
        try(FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, SaveData.class);
        }
    }
}
