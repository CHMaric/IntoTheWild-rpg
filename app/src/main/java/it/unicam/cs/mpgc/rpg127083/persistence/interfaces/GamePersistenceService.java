package it.unicam.cs.mpgc.rpg127083.persistence.interfaces;

import it.unicam.cs.mpgc.rpg127083.persistence.SaveData;
import java.io.IOException;
import java.util.List;

public interface GamePersistenceService {
    void saveGame(SaveData saveData, String slotName) throws IOException;
    SaveData loadGame(String slotName) throws IOException;
    List<String> getAvailableSlots();
    void deleteSave(String slotName) throws IOException;
}
