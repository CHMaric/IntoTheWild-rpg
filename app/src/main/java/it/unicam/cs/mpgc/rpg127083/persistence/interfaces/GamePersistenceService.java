package it.unicam.cs.mpgc.rpg127083.persistence.interfaces;

import it.unicam.cs.mpgc.rpg127083.persistence.SaveData;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GamePersistenceService {
    void saveGame(SaveData saveData, String slotName) throws IOException;
    SaveData loadGame(String slotName) throws IOException;
    List<String> getAvailableSlots();
    void deleteSave(String slotName) throws IOException;

    default CompletableFuture<Void> saveGameAsync(SaveData saveData, String slotName) {
        return CompletableFuture.runAsync(() -> {
            try {
                saveGame(saveData, slotName);
            } catch (IOException e) {
                throw new RuntimeException("Errore durante il salvataggio asincrono", e);
            }
        });
    }

    default CompletableFuture<SaveData> loadGameAsync(String slotName) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return loadGame(slotName);
            } catch (IOException e) {
                throw new RuntimeException("Errore durante il caricamento asincrono", e);
            }
        });
    }
}
