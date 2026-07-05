package it.unicam.cs.mpgc.rpg127083.persistence;

import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.GamePersistenceService;
import it.unicam.cs.mpgc.rpg127083.persistence.interfaces.SaveManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFilePersistenceService implements GamePersistenceService {
    private final SaveManager saveManager;
    private final String saveDirectory = "saves/";

    public JsonFilePersistenceService(SaveManager saveManager) {
        this.saveManager = saveManager;
        File folder = new File(saveDirectory);
        if(!folder.exists()) {
            folder.mkdirs();
        }
    }

    @Override
    public void saveGame(SaveData saveData, String slotName) throws IOException {
        String filePath = saveDirectory + slotName + ".json";
        saveManager.save(saveData, filePath);
    }

    @Override
    public SaveData loadGame(String slotName) throws IOException {
        String filePath = saveDirectory + slotName +".json";
        return saveManager.load(filePath);
    }

    @Override
    public List<String> getAvailableSlots() {
        File folder = new File(saveDirectory);
        File[] listOfFiles = folder.listFiles(
                (dir, name) -> name.toLowerCase().endsWith(".json"));
        List<String> slots = new ArrayList<>();
        if (listOfFiles != null)
            for (File f : listOfFiles)
                slots.add(f.getName().replace(".json", ""));
        return slots;
    }

    @Override
    public void deleteSave(String slotName) throws IOException {
        String filePath = saveDirectory + slotName + ".json";
        File file = new File(filePath);
        if(!file.exists())
            throw new IOException("Save file does not exist: " + filePath);
        if (!file.delete()) {
            throw new IOException("Failed to delete save file: " + filePath);
        }
    }
}
