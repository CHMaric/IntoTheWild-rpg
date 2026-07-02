package it.unicam.cs.mpgc.rpg127083.persistence.interfaces;

import it.unicam.cs.mpgc.rpg127083.persistence.SaveData;

import java.io.IOException;
import java.util.List;

public interface GamePersistenceService {
    void saveCurrentGame(SaveData saveData, String slotName) throws IOException;
    SaveData loadLastGame(String slotName) throws IOException;
    List<String> getAvailableSlots();
}
