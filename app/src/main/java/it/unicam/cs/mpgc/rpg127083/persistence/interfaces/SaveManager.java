package it.unicam.cs.mpgc.rpg127083.persistence.interfaces;

import it.unicam.cs.mpgc.rpg127083.persistence.SaveData;

import java.io.IOException;


public interface SaveManager {
    void save(SaveData data, String filePath) throws IOException;
    SaveData load(String filePath) throws IOException;

}
